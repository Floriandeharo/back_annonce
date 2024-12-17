package tech.chillo.avis_utilisateurs.security;

import java.security.Key;
import java.util.Date;
import java.util.List;

import tech.chillo.avis_utilisateurs.entity.Jwt;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Jwts;
import tech.chillo.avis_utilisateurs.entity.Utilisateur;
import tech.chillo.avis_utilisateurs.repository.JwtRepository;
import tech.chillo.avis_utilisateurs.service.UtilisateurService;
import java.time.Instant;
import java.util.logging.Logger;

@Transactional
@Service
public class JwtService {

	public static final String BEARER = "bearer";
	private final UtilisateurService utilisateurService;
	private final String ENCRIPTION_KEY = "a4799a2ab1e5117b84e988bb17d4930455b6883ef1ab499d7ce6b23c4da3e285";
	private JwtRepository jwtRepository;
    private static final Logger logger = Logger.getLogger(JwtService.class.getName());

	public JwtService(UtilisateurService utilisateurService,
					  JwtRepository jwtRepository) {
		this.utilisateurService = utilisateurService ;
		this.jwtRepository = jwtRepository ;
	}
	
	public Jwt tokenByValue(String value) {
		//System.out.print("value");
		//System.out.print(value);
		
		return this.jwtRepository.findByValeurAndDesactiveAndExpire(value, false,false)
				.orElseThrow(() -> new RuntimeException("Token invalide ou inconnu"));
	}
	
	public Map<String, String> generate(String username){
		Utilisateur utilisateur = (Utilisateur) this.utilisateurService.loadUserByUsername(username);
		this.disableTokens(utilisateur);
		final Map<String, String> jwtMap = this.generateJwt(utilisateur) ;
		final Jwt jwt = Jwt
			.builder()
			.valeur(jwtMap.get(BEARER))
			.desactive(false)
			.expire(false)
			.utilisateur(utilisateur)
			.build();
		this.jwtRepository.save(jwt);
		return jwtMap ;   
	}
	
	private  Map<String, String> generateJwt(Utilisateur utilisateur){
		final long currentTime = System.currentTimeMillis();
		final long expirationTime = currentTime + 30 + 6000 * 1000;
		final Map<String, Object> claims = Map.of(
				"nom",utilisateur.getNom(),
				Claims.SUBJECT, utilisateur.getEmail(),
				Claims.EXPIRATION, new Date(expirationTime)
				
			);
		

		final String bearer = Jwts.builder()
			.setIssuedAt(new Date(currentTime))
			.setExpiration(new Date(expirationTime))
			.setSubject(utilisateur.getEmail())
			.setClaims(claims)
			.signWith(getKey(), SignatureAlgorithm.HS256 )
			.compact();
			
		return Map.of(BEARER,bearer) ;
	}
	
	private Key getKey() {
		final byte[] decoder = Decoders.BASE64.decode(ENCRIPTION_KEY);
		return Keys.hmacShaKeyFor(decoder);
	}
	
	private void disableTokens(Utilisateur utilisateur) {
		final List<Jwt> jwtList = this.jwtRepository.findUtilisateur(utilisateur.getEmail()).peek(
				jwt -> {
					jwt.setDesactive(true);
					jwt.setExpire(true);
				}).collect(Collectors.toList());
		this.jwtRepository.saveAll(jwtList);
	}


	public  String extractUsername(String token) {
		// TODO Auto-generated method stub
		return this.getClaim(token, Claims::getSubject);
	}


	public boolean isTokenExpired(String token) {

		Date expirationDate = this.getClaim(token, Claims::getExpiration);; 
		return expirationDate.before(new Date()) ;
	}

	private <T> T getClaim(String token, Function<Claims, T>  function) {
		Claims claims = getAllClaims(token);
		return function.apply(claims);
	}


	private Claims getAllClaims(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(this.getKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}

	public List<Jwt> getAllJwt() {
		return this.jwtRepository.findAll();
	}

	public void deconnexion() {
		System.out.print("jwt deco");
		// TODO Auto-generated method stub
		Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Jwt jwt = this.jwtRepository.findByUtilisateurEmailAndDesactiveAndExpire( utilisateur.getEmail(), false, false).orElseThrow(() -> new RuntimeException("Token invalide"));
		jwt.setExpire(true);
		jwt.setDesactive(true);
		System.out.print(jwt);
		this.jwtRepository.save(jwt);
	}
	
	// @Scheduled(cron = "0 */1 * * * *")
	public void removeUselessJwt() {
		System.out.print("cron");
        logger.info("Suppression des tokens à " + Instant.now());
		this.jwtRepository.deleteAllByExpireAndDesactive(true, true);
	}
}
