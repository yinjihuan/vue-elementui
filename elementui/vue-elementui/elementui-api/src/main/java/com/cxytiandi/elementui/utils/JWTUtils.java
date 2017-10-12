package com.cxytiandi.elementui.utils;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import com.cxytiandi.elementui.base.ResponseCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

/**
 * API调用认证工具类，采用RSA加密
 * @author yinjihuan
 *
 */
public class JWTUtils {
	private static RSAPrivateKey priKey;
	private static RSAPublicKey pubKey;
	
	static {
		String modulus = "120749774428185480467622030722535804071445078993623309709775427878906293937338059423076695854937532244466465395164541641368876529295825453848760144835049363522545908104302024165873971414491110512342791594610742544380402908598585190494003507524195754273822268813447403290817343077571516216147839402414940310061";
		String publicExponent = "65537";
		String privateExponent = "73923469942286919561803730971048133587965873619209827001168953680477872428610977313161774128992838682156392947263251899461404460204267953359475632559803617319478756560848229397545070273747796303141458040475889804016062973476403760709402857872540300632704514872361476749953797952016730690123983122643596231873";
		priKey = RSAUtils.getPrivateKey(modulus, privateExponent);
		pubKey = RSAUtils.getPublicKey(modulus, publicExponent);
	}
	
	/**
	 * 获取Token
	 * @param uid 用户ID
	 * @param exp 失效时间，单位分钟
	 * @return
	 */
	public static String getToken(String uid, int exp) {
		long endTime = new Date().getTime() + 1000 * 60 * exp;
		return Jwts.builder().setSubject(uid).setExpiration(new Date(endTime))
				.signWith(SignatureAlgorithm.RS512, priKey).compact();
	}

	/**
	 * 获取Token
	 * @param uid 用户ID
	 * @param exp 默认失效时间为1天
	 * @return
	 */
	public static String getToken(String uid) {
		long endTime = new Date().getTime() + 1000 * 60 * 1440;
		return Jwts.builder().setSubject(uid).setExpiration(new Date(endTime))
				.signWith(SignatureAlgorithm.RS512, priKey).compact();
	}

	/**
	 * 检查Token是否合法
	 * @param token
	 * @return JWTResult
	 */
	public static JWTResult checkToken(String token) {
		try {
			Claims claims = Jwts.parser().setSigningKey(pubKey).parseClaimsJws(token).getBody();
			String sub = claims.get("sub", String.class);
			return new JWTResult(true, sub, "合法请求", ResponseCode.SUCCESS_CODE.getCode());
		} catch (ExpiredJwtException e) {
			// 在解析JWT字符串时，如果‘过期时间字段’已经早于当前时间，将会抛出ExpiredJwtException异常，说明本次请求已经失效
			return new JWTResult(false, null, "token已过期", ResponseCode.TOKEN_TIMEOUT_CODE.getCode());
		} catch (SignatureException e) {
			// 在解析JWT字符串时，如果密钥不正确，将会解析失败，抛出SignatureException异常，说明该JWT字符串是伪造的
			return new JWTResult(false, null, "非法请求", ResponseCode.NO_AUTH_CODE.getCode());
		} catch (Exception e) {
			return new JWTResult(false, null, "非法请求", ResponseCode.NO_AUTH_CODE.getCode());
		}
	}

	public static class JWTResult {
		private boolean status;
		private String uid;
		private String msg;
		private int code;
		
		public JWTResult() {
			super();
		}

		public JWTResult(boolean status, String uid, String msg, int code) {
			super();
			this.status = status;
			this.uid = uid;
			this.msg = msg;
			this.code = code;
		}
		
		public int getCode() {
			return code;
		}

		public void setCode(int code) {
			this.code = code;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		public boolean isStatus() {
			return status;
		}

		public void setStatus(boolean status) {
			this.status = status;
		}

		public String getUid() {
			return uid;
		}

		public void setUid(String uid) {
			this.uid = uid;
		}
	}
}
