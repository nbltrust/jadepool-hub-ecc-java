package com.jadepool.ecc;

import org.json.simple.JSONObject;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Sign;
import org.apache.commons.codec.binary.Base64;
import java.math.BigInteger;

public class Utils {

    public static String byteArrayToHex(byte[] data) {
        StringBuilder sb = new StringBuilder(data.length * 2);
        for(byte a: data)
            sb.append(String.format("%02x", a));
        return sb.toString();
    }

    public static String byteArrayToBase64(byte[] data) {
        return java.util.Base64.getEncoder().encodeToString(data);
    }

    public static String base64ToHex(String data) {
        return byteArrayToHex(Base64.decodeBase64(data));
    }

    static JSONObject sign (byte[] byteArr, String key) {
        BigInteger privKey = new BigInteger(key,16);
        BigInteger pubKey = Sign.publicKeyFromPrivate(privKey);
        ECKeyPair keyPair = new ECKeyPair(privKey, pubKey);
        Sign.SignatureData sig = Sign.signMessage(byteArr, keyPair, false);

        JSONObject signature = new JSONObject();
        signature.put("r", Utils.byteArrayToBase64(sig.getR()));
        signature.put("s", Utils.byteArrayToBase64(sig.getS()));
        signature.put("v", sig.getV());

        return signature;
    }

    static Sign.SignatureData buildSignature (JSONObject o) {
        Long vObject = (Long) o.get("v");
        String rObject = (String) o.get("r");
        String sObject = (String) o.get("s");
        byte v = vObject.byteValue();
        byte[] r = Base64.decodeBase64(rObject);
        byte[] s = Base64.decodeBase64(sObject);
        Sign.SignatureData signature = new Sign.SignatureData(v, r, s);

        return signature;
    }
}
