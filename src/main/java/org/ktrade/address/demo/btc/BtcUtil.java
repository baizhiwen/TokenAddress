package org.ktrade.address.demo.btc;

import org.bitcoinj.core.Address;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.params.TestNet3Params;
import org.ktrade.address.demo.coin.BtcCoinAddress;
import org.ktrade.address.demo.util.FileUtil;
import org.ktrade.address.demo.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class BtcUtil {
    private final static Logger logger = LoggerFactory.getLogger(BtcUtil.class);

    /**
     * 获得btc地址和私钥
     *
     * @param count
     * @return
     */
    public List<BtcCoinAddress> getBtcCoinAddressList(int count) {
        List<BtcCoinAddress> btcCoinAddressList = new ArrayList<BtcCoinAddress>();
        // use test net by default
        String net = "prod";

        final NetworkParameters netParams;
        if (net.equals("prod")) {
            netParams = MainNetParams.get();//NetworkParameters.prodNet();
        } else {
            netParams = TestNet3Params.get();//NetworkParameters.testNet();
        }


        for (int i = 0; i < count; i++) {
            // create a new EC Key ...
            ECKey key = new ECKey();
            // get valid Bitcoin address from public key
            Address addressFromKey = key.toAddress(netParams);

            BtcCoinAddress btcCoinAddress = new BtcCoinAddress();
            btcCoinAddress.setCoinAddress(addressFromKey.toString());
            btcCoinAddress.setPublicKey(key.getPublicKeyAsHex());
            btcCoinAddress.setPrivateKey(key.getPrivateKeyAsHex());

            btcCoinAddressList.add(btcCoinAddress);
        }

        return btcCoinAddressList;
    }

    public static void main(String[] args) throws Exception {

        int count = 5000;    //BTC个数
        String filePath = "d:/btc_test.txt"; //生成文件位置，会自动覆盖

        BtcUtil btcUtil = new BtcUtil();
        List<BtcCoinAddress> btcList = btcUtil.getBtcCoinAddressList(count);

        StringBuffer sb = new StringBuffer("BTC 地址、私钥、公钥\n");
        for (int i = 0, size = btcList.size(); i < size; i++) {
            BtcCoinAddress btcCoinAddress = btcList.get(i);
            String idx = StringUtil.toLength(i + 1 + "", String.valueOf(count).length());
            //sb.append(idx).append(".")
            sb.append(btcCoinAddress.getCoinAddress()).append("\t")
                    .append(btcCoinAddress.getPrivateKey()).append("\t")
                    .append(btcCoinAddress.getPublicKey()).append("\n");
            //System.out.println(list.get(i));
        }

        FileUtil.write(filePath, sb.toString());
        System.out.println(filePath + " " + count + "个地址");

    }
}
