package com.riven.daggermvp.net;

import android.content.Context;
import android.text.TextUtils;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/**
 * Description: https 的证书
 * Author: djs
 * Date: 2019/6/4.
 */
public class HTTPSTools {

    public static SSLSocketFactory getSSLSocketFactory(Context context, int[] certificates) {
        if (null != certificates && certificates.length > 0){
            return getSSLSocketFactory1(context, certificates);
        }else{
            return getSSLSocketFactory2();
        }
    }



    /**
     * 获取证书
     *
     * @param context
     * @param certificates
     * @return
     */
    public static SSLSocketFactory getSSLSocketFactory1(Context context, int[] certificates) {

        if (context == null) {
            throw new NullPointerException("context == null");
        }

        CertificateFactory certificateFactory;
        try {
            certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null);
            if (null != certificates && certificates.length > 0) {
                for (int i = 0; i < certificates.length; i++) {
                    InputStream certificate = context.getResources().openRawResource(certificates[i]);
                    keyStore.setCertificateEntry(String.valueOf(i), certificateFactory.generateCertificate(certificate));

                    if (certificate != null) {
                        certificate.close();
                    }
                }
            }
            SSLContext sslContext = SSLContext.getInstance("TLS");
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());

            return sslContext.getSocketFactory();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     *  不提证书请求
     * @return
     */
    public static SSLSocketFactory getSSLSocketFactory2() {
        TrustManager[] trustManagers = new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[0];
                    }
                }
        };


        try {

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustManagers, new SecureRandom());

            return sslContext.getSocketFactory();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * 支持指定host
     * @param host
     * @return
     */
    public static HostnameVerifier getHostnameVerifier(final String host) {

        HostnameVerifier TRUSTED_VERIFIER = new HostnameVerifier() {

            @Override
            public boolean verify(String hostname, SSLSession session) {
//                return true;
                if (TextUtils.isEmpty(host)){
                    return true;
                }
                boolean ret = false;
                if (!TextUtils.isEmpty(host)) {
                    if (host.equalsIgnoreCase(hostname)) {
                        ret = true;
                    }
                }
                return ret;
            }
        };

        return TRUSTED_VERIFIER;
    }


    public static SSLSocketFactory getSSLSocketFactoryValue(Context context, int[] certificates){
        try {

            SSLContext sslContext = SSLContext.getInstance("TLS");

            sslContext.init(null, getX509TrustManager(context, certificates), new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static TrustManager[] getX509TrustManager(Context context, int[] certificates){
        if (null != certificates && certificates.length > 0){
            return getX509TrustManager1(context,certificates);
        }else{
            return getX509TrustManager2();
        }
    }


    public static TrustManager[] getX509TrustManager1(Context context, int[] certificates){


        if (context == null) {
            throw new NullPointerException("context == null");
        }

        CertificateFactory certificateFactory;
        try {
            certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null);
            if (null != certificates && certificates.length > 0) {
                for (int i = 0; i < certificates.length; i++) {
                    InputStream certificate = context.getResources().openRawResource(certificates[i]);
                    keyStore.setCertificateEntry(String.valueOf(i), certificateFactory.generateCertificate(certificate));

                    if (certificate != null) {
                        certificate.close();
                    }
                }
            }

            final TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);

            return trustManagerFactory.getTrustManagers();


        } catch (IOException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }

        return null;

    }


    public static TrustManager[] getX509TrustManager2(){

        TrustManager trustManager = new X509TrustManager() {

            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };

        return new TrustManager[]{trustManager};
    }


}
