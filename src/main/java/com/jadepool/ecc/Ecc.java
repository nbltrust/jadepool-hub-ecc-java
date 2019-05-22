package com.jadepool.ecc;

import com.google.gson.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.web3j.crypto.Hash;
import org.web3j.crypto.Sign;
import org.apache.commons.codec.binary.Base64;

import java.math.BigInteger;
import java.util.*;

public class Ecc {

    private String privateKey;
    private String jadePubKey;

    public Ecc() {}

    public Ecc(String privateKey) {
        this.privateKey = Utils.base64ToHex(privateKey);
    }

    public Ecc(String privateKey, String jadePubKey) {
        this.privateKey = Utils.base64ToHex(privateKey);
        this.jadePubKey = jadePubKey;
    }

    /**
     * sign request
     * @param data all parameters in request's data object, in json string format
     * @param timestamp the (current) timestamp in request
     * @return signature
     * @throws Exception
     */
    public String sign(String data, Long timestamp) throws Exception {
        if (data == null) {
            throw new Exception("Invalid data!");
        }

        String res = preprocess(data, timestamp);
        byte[] preSignJsonMessageByteArr = res.getBytes();
        byte[] preSignJsonMessageSha = Hash.sha3(preSignJsonMessageByteArr);
        JSONObject sig = Utils.sign(preSignJsonMessageSha, this.privateKey);

        return sig.toJSONString();
    }

    /**
     * Verify signature
     * @param response HTTP response body or callback, in json string format
     * @return boolean
     * @throws Exception
     */
    public boolean verify(String response) throws Exception {
        if (response == null) {
            throw new Exception("Invalid response!");
        }
        JSONParser parser = new JSONParser();
        JSONObject responseObject = (JSONObject)parser.parse(response);
        Long code = (Long)responseObject.get("code");
        if (code.longValue() != 0) {
            throw new Exception("Reqeust failed!");
        }

        String resultObjectString = responseObject.get("result").toString();
        Long timestamp = (Long) responseObject.get("timestamp");
        String res = preprocess(resultObjectString, timestamp);

        JSONObject sigObject = (JSONObject) responseObject.get("sig");
        Sign.SignatureData signature = Utils.buildSignature(sigObject);

        BigInteger pubKeyRecovered = Sign.signedMessageToKey(res.getBytes(), signature);
        BigInteger correctPubKey = new BigInteger(1, Arrays.copyOfRange(Base64.decodeBase64(this.jadePubKey), 1, Base64.decodeBase64(this.jadePubKey).length));

        if (!correctPubKey.equals(pubKeyRecovered)) {
            return false;
        }

        return true;
    }

    private static String preprocess(String result, Long timestamp) {
        String res = null;
        try {
            Gson g = new GsonBuilder().setPrettyPrinting().create();
            JsonParser p = new JsonParser();
            JsonObject inputObj  = g.fromJson(result, JsonObject.class);
            inputObj.addProperty("timestamp", timestamp.toString());
            String newStr = g.toJson(inputObj);
            JsonElement e = p.parse(newStr);
            EccUtils.sort(e);
            res = EccUtils.toStr(e);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return res;
    }

    public String getJadePubKey() {
        return jadePubKey;
    }

    public void setJadePubKey(String jadePubKey) {
        this.jadePubKey = jadePubKey;
    }
}
