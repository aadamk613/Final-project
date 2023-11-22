package com.kh.finalproject.member.model.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

@Service
public class KakaoLoginService {

  public String getToken(String code) throws IOException, ParseException {

    String kakaoUrl = "https://kauth.kakao.com/oauth/token";
    URL url = new URL(kakaoUrl);
    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

    urlConnection.setRequestMethod("POST");
    urlConnection.setDoOutput(true);

    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream()));
    StringBuilder sb = new StringBuilder();
    sb.append("client_id=5beb2b3c082749b54d59123ddd3d6c01");
    sb.append("&grant_type=authorization_code");
    sb.append("&redirect_uri=http://localhost:8001/final/kakaoLogin.me");
    sb.append("&code=" + code);

    bw.write(sb.toString());
    bw.flush();

    // System.out.println(urlConnection.getResponseCode());

    BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
    String line = "";
    String responseData = "";
    while ((line = br.readLine()) != null) {
      responseData += line;
    }
    // System.out.println(responseData);

    JSONParser parser = new JSONParser();
    JSONObject element = (JSONObject) parser.parse(responseData);

    String accessToken = element.get("access_token").toString();

    br.close();
    bw.close();

    return accessToken;
  }

  public Map<String, String> getUserInfo(String accessToken) throws IOException, ParseException {

    String kakaoUrl = "https://kapi.kakao.com/v2/user/me";

    URL url = new URL(kakaoUrl);

    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
    urlConnection.setRequestProperty("Authorization", "Bearer " + accessToken);
    urlConnection.setRequestMethod("GET");

    System.out.println(urlConnection.getResponseCode());

    BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
    String line = "";
    String responseData = "";
    while ((line = br.readLine()) != null) {
      responseData += line;
    }

    JSONObject responseObj = (JSONObject) new JSONParser().parse(responseData);
    JSONObject kakaoAccount = (JSONObject) responseObj.get("properties");

    System.out.println(kakaoAccount);
    System.out.println(responseObj.get("id").toString());
    Map<String, String> returnMe = new HashMap<>();
    returnMe.put("id", responseObj.get("id").toString());
    returnMe.put("nickname", kakaoAccount.get("nickname").toString());
    returnMe.put("profile_image", kakaoAccount.get("profile_image").toString());
    return returnMe;
  }
}
