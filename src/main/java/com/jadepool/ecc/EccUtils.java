package com.jadepool.ecc;

import org.apache.commons.codec.binary.Base64;
import org.json.simple.JSONObject;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Sign;

import java.math.BigInteger;

public class EccUtils {


    /**
     * Generate a ECC private key
     * @return string
     * @throws Exception
     */
    public static String generatePrivateKey() throws Exception {
        BigInteger privKey = Keys.createEcKeyPair().getPrivateKey();
        String privateKey = Utils.byteArrayToBase64(privKey.toByteArray());
        return privateKey;
    }

    /**
     * Derive ECC public key from private key
     * @param privateKey private Key, in base64 format
     * @return string
     */
    public static String privateToPublic(String privateKey) {
        BigInteger privKey = new BigInteger(Base64.decodeBase64(privateKey));
        BigInteger pubKey = Sign.publicKeyFromPrivate(privKey);
        String publicKey = Utils.byteArrayToBase64(pubKey.toByteArray());
        return publicKey;
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
