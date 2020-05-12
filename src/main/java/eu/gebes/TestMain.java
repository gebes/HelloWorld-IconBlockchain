package eu.gebes;

import foundation.icon.icx.*;
import foundation.icon.icx.crypto.KeystoreException;
import foundation.icon.icx.data.*;
import foundation.icon.icx.transport.http.HttpProvider;
import foundation.icon.icx.transport.jsonrpc.RpcObject;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class TestMain {

    /*
    $ docker container start -a local-tbears
    root@07dfee84208e:/tbears#

    $ docker container attach local-tbears
    root@07dfee84208e:/tbears#
     */

    public static Address scoreAddress = new Address("cxe2b7a34c49ad21d06a203e9abd9357f400b0483e");

    public static void main(String[] args) throws IOException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, KeystoreException, InterruptedException {

        // Creates an instance of IconService using the HTTP provider.
        IconService iconService = new IconService(new HttpProvider("http://localhost:9000", 3));
        Wallet wallet = loadWallet();


        header("All Score functions");
        var score = iconService.getScoreApi(scoreAddress).execute();
        for (var scoreApi : score)
            System.out.println(scoreApi.getName());


        header("Increment Transaction");
        Transaction incrementTransaction = TransactionBuilder.newBuilder()
                .nid(NetworkId.TEST)
                .from(wallet.getAddress())
                .to(scoreAddress)
                .value(new BigInteger("150000000"))
                .stepLimit(new BigInteger("1000000"))
                .nonce(new BigInteger("1000000"))
                .call("increment")
                .params(new RpcObject.Builder().build())
                .build();


        SignedTransaction signedTransaction = new SignedTransaction(incrementTransaction, wallet);
        Bytes resultHash = iconService.sendTransaction(signedTransaction).execute();


        Thread.sleep(2000);

        var transactionResult = iconService.getTransactionResult(resultHash).execute();
        System.out.println("Transaction Status: " + transactionStatusString(transactionResult));
        System.out.println(transactionResult);


        header("Read my current value out");
        var call = new Call.Builder()
                .from(wallet.getAddress())
                .to(scoreAddress)
                .method("my_value")
                .buildWith(String.class);

        var result = iconService.call(call).execute();
        System.out.println(result);


    }

    private static void header(String title) {
        System.out.println("\n= " + title + " " + ("=".repeat(100 - title.length())));
    }

    private static Wallet loadWallet() throws IOException, KeystoreException {
        File file = new File("./keystore_test1");
        return KeyWallet.load("test1_Account", file);
    }

    private static String transactionStatusString(TransactionResult transactionResult){
        return transactionResult.getStatus().equals(BigInteger.ONE) ? "success" : "failure";
    }

}
