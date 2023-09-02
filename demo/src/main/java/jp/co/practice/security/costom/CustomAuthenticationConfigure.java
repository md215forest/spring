package jp.co.practice.security.costom;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * CustomAuthenticationFilterを初期化するためのクラス
 * @param <H>
 */
public class CustomAuthenticationConfigure<H extends HttpSecurityBuilder<H>> extends AbstractAuthenticationFilterConfigurer<H, CustomAuthenticationConfigure<H>, CustomAuthenticationFilter> {
	public CustomAuthenticationConfigure() {
		super(new CustomAuthenticationFilter(),"/token");
	}

	@Override
	protected RequestMatcher createLoginProcessingUrlMatcher(String loginProcessingUrl) {
		return new AntPathRequestMatcher(loginProcessingUrl, "GET");
	}

	@Override
	public void configure(H http) {

		this.getAuthenticationFilter().setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));

		SessionAuthenticationStrategy sessionAuthenticationStrategy = http.getSharedObject(SessionAuthenticationStrategy.class);
		this.getAuthenticationFilter().setSessionAuthenticationStrategy(sessionAuthenticationStrategy);

		RememberMeServices rememberMeServices = http.getSharedObject(RememberMeServices.class);
		if (rememberMeServices != null) {
			this.getAuthenticationFilter().setRememberMeServices(rememberMeServices);
		}

		SecurityContextRepository securityContextRepository = http.getSharedObject(SecurityContextRepository.class);
		if(securityContextRepository != null){
			this.getAuthenticationFilter().setSecurityContextRepository(securityContextRepository);
		}

		this.getAuthenticationFilter().setSecurityContextHolderStrategy(getSecurityContextHolderStrategy());

		http.addFilterBefore(this.getAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class);

	}

}
