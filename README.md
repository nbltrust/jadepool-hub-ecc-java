# jadepool-ecc-java

```java
    import com.jadepool.ecc.Ecc;
    import org.json.simple.JSONObject;
    import java.sql.Timestamp;
    
    public class Main {
    
        public static void main(String[] args) throws Exception {
            
            // Sign request and get signature
            String privateKey = "feDhgnJGkNMWSmPlbMtC+4DmUruJAtdCu4j3Vxu2CWM=";
            Ecc ecc = new Ecc(privateKey);
            
            // request withdrawal data parameters
            long sequence = 1;
            String to = "n4he3WuSAKJdY58ReeXtC8cMse6ip6GL1S";
            String type = "BTC";
            String value = "0.1";
            
            JSONObject object = new JSONObject();
            object.put("sequence", sequence);
            object.put("to", to);
            object.put("type", coinId);
            object.put("value", value);
            
            long timestamp = (new Timestamp(System.currentTimeMillis())).getTime();
            String signature = ecc.sign(object.toJSONString(), timestamp);
            
            // verify signature in response or callback
            ecc.setJadePubKey("BKzjJTLJBlLhuukWJI5CenqxCu7qEGeUlmmj9NoQll75DXKX9TjyMAajH5T9z67Z6N04yFun4oX3J0MDMpJa7+U=");
            String response = "{\n" +
                            "    \"code\": 0,\n" +
                            "    \"status\": 0,\n" +
                            "    \"message\": \"OK\",\n" +
                            "    \"crypto\": \"ecc\",\n" +
                            "    \"timestamp\": 1558076348165,\n" +
                            "    \"sig\": {\n" +
                            "        \"r\": \"KiEhjv3GO2CPloOHHDQLj9ETMGta5duP5ZL0Wd5oxWA=\",\n" +
                            "        \"s\": \"em9opR2TQCcCjvHRL4yFpZ1fiZs1HBeEudUwI6b+76g=\",\n" +
                            "        \"v\": 27\n" +
                            "    },\n" +
                            "    \"result\": {\n" +
                            "        \"balance\": \"0\",\n" +
                            "        \"balanceAvailable\": \"0\",\n" +
                            "        \"balanceUnavailable\": \"0\",\n" +
                            "        \"update_at\": 1558076348163,\n" +
                            "        \"addressesWithBalance\": 1,\n" +
                            "        \"namespace\": \"Eosio\",\n" +
                            "        \"sid\": \"FHvzET7r64eyXYgtAAAD\"\n" +
                            "    }\n" +
                            "}";
            ecc.verify(response);
        }
    }
```