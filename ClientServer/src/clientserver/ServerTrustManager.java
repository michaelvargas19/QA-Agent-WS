/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientserver;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/**
 *
 * @author USUARIO
 */
public class ServerTrustManager implements X509TrustManager{
 private String trustStorePath;
  private X509TrustManager trustManager;
  private ArrayList<Certificate> certList;

  public ServerTrustManager(String trustStorePath) throws Exception {
    this.trustStorePath = trustStorePath;
    certList = new ArrayList<Certificate>();
    reloadTrustManager();
  }

  @Override
  public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    trustManager.checkClientTrusted(chain, authType);
  }

  @Override
  public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    try {
      trustManager.checkServerTrusted(chain, authType);
    } catch (CertificateException cx) {
      // Development logic only. Trusts the incoming untrusted certificate.
      // addCertificate(chain[0]);
      // reloadTrustManager();
      // trustManager.checkServerTrusted(chain, authType);
    }
  }

  @Override
  public X509Certificate[] getAcceptedIssuers() {
    return trustManager.getAcceptedIssuers();
  }

  /**
   * Instantiates a new X509TrustManager with the latest certificates
   */
  private void reloadTrustManager() throws Exception {
    // load keystore from specified cert store (or default)
    KeyStore ts = KeyStore.getInstance(KeyStore.getDefaultType());
    InputStream in = new FileInputStream(trustStorePath);
    try { 
      ts.load(in, null); 
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      in.close(); 
    }

    /*
    // Add queued certificates to the keystore
    for (Certificate cert : tempCertList) {
      ts.setCertificateEntry(UUID.randomUUID(), cert);
    }
    */
    // Initialize a new TrustManagerFactory with the truststore we created
    TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
    tmf.init(ts);

    // Get the X509TrustManager from the Factory.
    TrustManager tms[] = tmf.getTrustManagers();
    for (int i = 0; i < tms.length; i++) {
      if (tms[i] instanceof X509TrustManager) {
        trustManager = (X509TrustManager) tms[i];
        return;
      }
    }

    throw new NoSuchAlgorithmException("No X509TrustManager in TrustManagerFactory");
  }

  /**
   * Adds cert to the TrustManager. Automatically reloads the TrustManager
   * @param cert is not null
   * @throws Exception if cannot be reloaded
   */
  private void addCertificate(Certificate cert) throws Exception {
    certList.add(cert);
    reloadTrustManager();
  }

  /**
   * Removes a certificate from the pending list. Automatically reloads the TrustManager
   * @param cert is not null and was already added
   * @throws Exception if cannot be reloaded
   */
  public void removeCertificate(Certificate cert) throws Exception {
    certList.remove(cert);
    reloadTrustManager();
  }

  /**
   * Adds a list of certificates to the manager. Automatically reloads the TrustManager
   * @param certs is not null
   * @throws Exception if cannot be reloaded
   */
  public void addCertificates(List<Certificate> certs) throws Exception {
    certList.addAll(certs);
    reloadTrustManager();
  }

}
