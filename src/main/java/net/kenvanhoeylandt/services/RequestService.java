package net.kenvanhoeylandt.services;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.nio.charset.Charset;

import bolts.Task;

/**
 * Generic service to create and execute HTTP requests through the OkHttp library.
 */
public class RequestService
{
	private static RequestService sInstance;

	private OkHttpClient mClient;

	private final CookieManager mCookieManager = new CookieManager();

	public static RequestService shared()
	{
		if (sInstance == null)
		{
			sInstance = new RequestService();
		}

		return sInstance;
	}

	private RequestService()
	{
		CookieHandler.setDefault(mCookieManager);
		mCookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
	}

	protected OkHttpClient createClient()
	{
		return new OkHttpClient();
	}

	protected final OkHttpClient getClient()
	{
		if (mClient == null)
		{
			mClient = createClient();
			mClient.setCookieHandler(mCookieManager);
		}

		return mClient;
	}

	public CookieStore getCookieStore()
	{
		return mCookieManager.getCookieStore();
	}

	public Request.Builder requestBuilder()
	{
		return new Request.Builder();
	}

	public Task<Response> execute(final Request request)
	{
		return Task.callInBackground(() -> getClient().newCall(request).execute());
	}

	public Task<String> executeForString(final Request request)
	{
		return execute(request)
			.onSuccess(task -> {
				Response response = task.getResult();
				return response.body().source().readString(Charset.forName("UTF-8"));
			});
	}

	public Task<JSONObject> executeForJsonObject(final Request request)
	{
		return executeForString(request).onSuccess(task -> new JSONObject(task.getResult()));
	}

	public Task<JSONArray> executeForJsonArray(final Request request)
	{
		return executeForString(request).onSuccess(task -> new JSONArray(task.getResult()));
	}
}
