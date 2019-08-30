package com.jadepool.ecc;

import org.junit.Test;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class EccTest {

    Ecc ecc = new Ecc();

    @Test
    public void testVerifyOrderCallback() throws Exception {
        ecc.setJadePubKey("BK8H2i2V1QarXSWIK8kVhMCkdtaUR8LLFVxe6TtV7yWE4xsgwkCOENEUTD62YYuckuju/QivwJHaFlRY45GCxiE=");
        String orderCallback = "{\n" +
                "    \"code\": 0,\n" +
                "    \"status\": 0,\n" +
                "    \"message\": \"OK\",\n" +
                "    \"crypto\": \"ecc\",\n" +
                "    \"timestamp\": 1551926531651,\n" +
                "    \"sig\": {\n" +
                "        \"r\": \"SozZfWiFotVLrcF0/JpiWcl55ckaaqffZT0nt+vAiCY=\",\n" +
                "        \"s\": \"W208ReFXYx44ekmlmbeN4iX5uOMG3+JRjN9O5LRg13Y=\",\n" +
                "        \"v\": 27\n" +
                "    },\n" +
                "    \"result\": {\n" +
                "        \"id\": \"6874\",\n" +
                "        \"state\": \"done\",\n" +
                "        \"bizType\": \"WITHDRAW\",\n" +
                "        \"type\": \"ATOM\",\n" +
                "        \"coinType\": \"ATOM\",\n" +
                "        \"to\": \"cosmos1hztxhgqqwusuze3emmjyyrzsh3glr2qlj7qxmt\",\n" +
                "        \"value\": \"2\",\n" +
                "        \"confirmations\": 124818,\n" +
                "        \"create_at\": 1551176412281,\n" +
                "        \"update_at\": 1551176455757,\n" +
                "        \"from\": \"cosmos1hrnl5pugag20gnu8zy3604jetqrjaf60czkl8u\",\n" +
                "        \"n\": 0,\n" +
                "        \"fee\": \"1\",\n" +
                "        \"fees\": [\n" +
                "            {\n" +
                "                \"_id\": \"5c75130782f73d886a32a38a\",\n" +
                "                \"amount\": \"1\",\n" +
                "                \"nativeName\": \"muon\",\n" +
                "                \"coinName\": \"ATOM\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"_id\": \"5c75130782f73d886a32a389\",\n" +
                "                \"amount\": \"3\",\n" +
                "                \"nativeName\": \"photino\",\n" +
                "                \"coinName\": \"PTNO\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"data\": {\n" +
                "            \"timestampBegin\": 1551176435728,\n" +
                "            \"timestampFinish\": 1551176455644,\n" +
                "            \"timestampHandle\": 1551176435607,\n" +
                "            \"type\": \"Cosmos\",\n" +
                "            \"hash\": \"3579467CEA40F783D5964FAE999692D3B4FDF7111F5035911EE732C1A5D3FE28\",\n" +
                "            \"blockNumber\": 106930,\n" +
                "            \"blockHash\": \"95D28E04AAE4FF50081715D958BBDE871440AE5A39F0C57B19B38139E38BC698\",\n" +
                "            \"from\": [\n" +
                "                {\n" +
                "                    \"address\": \"cosmos1hrnl5pugag20gnu8zy3604jetqrjaf60czkl8u\",\n" +
                "                    \"value\": \"2\",\n" +
                "                    \"tokenName\": \"muon\",\n" +
                "                    \"coinName\": \"ATOM\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"to\": [\n" +
                "                {\n" +
                "                    \"address\": \"cosmos1hztxhgqqwusuze3emmjyyrzsh3glr2qlj7qxmt\",\n" +
                "                    \"value\": \"2\",\n" +
                "                    \"tokenName\": \"muon\",\n" +
                "                    \"coinName\": \"ATOM\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"fee\": \"1,muon,ATOM;3,photino,PTNO\",\n" +
                "            \"confirmations\": 124818,\n" +
                "            \"state\": \"done\"\n" +
                "        },\n" +
                "        \"hash\": \"3579467CEA40F783D5964FAE999692D3B4FDF7111F5035911EE732C1A5D3FE28\",\n" +
                "        \"block\": 106930,\n" +
                "        \"extraData\": \"automation-test\",\n" +
                "        \"memo\": \"\",\n" +
                "        \"sendAgain\": false,\n" +
                "        \"namespace\": \"Cosmos\",\n" +
                "        \"sid\": \"5de9plikE3XKN-4sAAAB\"\n" +
                "    }\n" +
                "}";
        boolean isValid = ecc.verify(orderCallback);
        assertTrue(isValid);
    }

    @Test
    public void testVerifyAuditCallback() throws Exception {
        ecc.setJadePubKey("BK8H2i2V1QarXSWIK8kVhMCkdtaUR8LLFVxe6TtV7yWE4xsgwkCOENEUTD62YYuckuju/QivwJHaFlRY45GCxiE=");
        String auditCallback = "{\n" +
                "  \"code\": 0,\n" +
                "  \"status\": 0,\n" +
                "  \"message\": \"OK\",\n" +
                "  \"crypto\": \"ecc\",\n" +
                "  \"timestamp\": 1551283200000,\n" +
                "  \"sig\": {\n" +
                "    \"r\": \"zCyr1fXrApBw1pYqtg0Tlw0kP9ky8OBqOI9Ov+tvZO4=\",\n" +
                "    \"s\": \"ChS7wi3oov5VgV+YV6esbsnAEYuGmEpL0P7f73GuhaA=\",\n" +
                "    \"v\": 27\n" +
                "  },\n" +
                "  \"result\": {\n" +
                "    \"current\": {\n" +
                "      \"id\": \"5c7743ab82f73d886a337e00\",\n" +
                "      \"type\": \"ATOM\",\n" +
                "      \"blocknumber\": 125148,\n" +
                "      \"timestamp\": 1551283200000,\n" +
                "      \"deposit_total\": \"2\",\n" +
                "      \"withdraw_total\": \"2\",\n" +
                "      \"fee_total\": \"0\",\n" +
                "      \"internal_fee\": \"0\",\n" +
                "      \"internal_num\": 0\n" +
                "    },\n" +
                "    \"calculated\": true,\n" +
                "    \"deposit_total\": \"2\",\n" +
                "    \"deposit_num\": 2,\n" +
                "    \"withdraw_total\": \"2\",\n" +
                "    \"withdraw_num\": 2,\n" +
                "    \"sweep_total\": \"0\",\n" +
                "    \"sweep_num\": 0,\n" +
                "    \"sweep_internal_total\": \"0\",\n" +
                "    \"sweep_internal_num\": 0,\n" +
                "    \"airdrop_total\": \"0\",\n" +
                "    \"airdrop_num\": 0,\n" +
                "    \"recharge_total\": \"4\",\n" +
                "    \"recharge_num\": 1,\n" +
                "    \"recharge_internal_total\": \"0\",\n" +
                "    \"recharge_internal_num\": 0,\n" +
                "    \"recharge_unknown_total\": \"8\",\n" +
                "    \"recharge_unknown_num\": 4,\n" +
                "    \"recharge_special_total\": \"0\",\n" +
                "    \"recharge_special_num\": 0,\n" +
                "    \"failed_withdraw_num\": 0,\n" +
                "    \"failed_sweep_num\": 0,\n" +
                "    \"failed_sweep_internal_num\": 0,\n" +
                "    \"fees\": [\n" +
                "      {\n" +
                "        \"withdraw_fee\": \"2\",\n" +
                "        \"sweep_fee\": \"0\",\n" +
                "        \"sweep_internal_fee\": \"0\",\n" +
                "        \"failed_withdraw_fee\": \"0\",\n" +
                "        \"failed_sweep_fee\": \"0\",\n" +
                "        \"failed_sweep_internal_fee\": \"0\",\n" +
                "        \"_id\": \"5c7f7c71ae41209c0fc8417c\",\n" +
                "        \"fee_type\": \"PTNO\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"type\": \"ATOM\",\n" +
                "    \"timestamp\": 1551283200000,\n" +
                "    \"blocknumber\": 125148,\n" +
                "    \"create_at\": \"2019-02-28T02:12:59.048Z\",\n" +
                "    \"update_at\": \"2019-03-06T07:53:21.985Z\",\n" +
                "    \"__v\": 3,\n" +
                "    \"calc_order_num\": 9,\n" +
                "    \"last\": \"5c75f22b82f73d886a32fad5\",\n" +
                "    \"id\": \"5c7743ab82f73d886a337e00\"\n" +
                "  }\n" +
                "}";
        boolean isValid = ecc.verify(auditCallback);
        assertTrue(isValid);
    }

    @Test
    public void testVerifyResponse() throws Exception {
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
        boolean isValid = ecc.verify(response);
        assertTrue(isValid);
    }

    @Test
    public void testSign() throws Exception {
        Ecc ecc = new Ecc("cYjKmvthKqVuFI29l5Xo+LHtkfJlIs0YnwEwXawW4NY=");
        String requestData = "{\"type\":\"TRX\"}";
        Long timestamp = new Long(1567078754623l);
        String sig = ecc.sign(requestData, timestamp);

        assertEquals("4797a0375f4fb1f303714c3871f232ae8f4ce77f110310d92340bca953ee656674f59d3a12034393538d5a783520d830b977e282c76f0faf21fcf363835300c5", sig);
    }

    @Test
    public void testGenerateKeyPair() throws Exception {
        String privateKey = EccUtils.generatePrivateKey();
        assertTrue(Utils.isBase64(privateKey));

        String publicKey = EccUtils.privateToPublic(privateKey);
        assertTrue(Utils.isBase64(publicKey));
    }
}
