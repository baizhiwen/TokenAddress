package org.ktrade.address.demo.coin;


public class CoinAddressBase {
    private String coinAddress;
    private String publicKey;
    private String privateKey;

    public String getCoinAddress() {
        return coinAddress;
    }

    public void setCoinAddress(String coinAddress) {
        this.coinAddress = coinAddress;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    @Override
    public String toString() {
        /*return "CoinAddressBase{" +
                "coinAddress='" + coinAddress + '\'' +
                ", publicKey='" + publicKey + '\'' +
                ", privateKey='" + privateKey + '\'' +
                '}';*/
        return coinAddress +","+privateKey+","+publicKey;
    }
}
