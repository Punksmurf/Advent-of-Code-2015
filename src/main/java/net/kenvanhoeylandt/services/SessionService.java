package net.kenvanhoeylandt.services;

import java.net.HttpCookie;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Manages the session token for authentication.
 */
public class SessionService
{
	private static SessionService sInstance;

	public static SessionService shared()
	{
		if (sInstance == null)
		{
			sInstance = new SessionService();
		}

		return sInstance;
	}

	private SessionService()
	{
	}

	public void setSessionToken(String sessionToken)
	{
		setAuthenticationCookie(sessionToken);
	}

	private void setAuthenticationCookie(String sessionToken)
	{
		HttpCookie cookie = new HttpCookie("session", sessionToken);
		cookie.setPath("/");
		cookie.setVersion(0);
		cookie.setDomain("adventofcode.com");

		try
		{
			RequestService.shared().getCookieStore().add(new URI("http://adventofcode.com"), cookie);
		}
		catch (URISyntaxException e)
		{
			e.printStackTrace();
		}
	}
}
