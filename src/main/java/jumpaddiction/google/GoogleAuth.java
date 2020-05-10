/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumpaddiction.google;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.AppendValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.util.Arrays;
import java.util.List;

/**
 * Googlen spreadsheet apin käyttöä varten oleva luokka. Käyttää service tunnusta autentikoinnissa Googlen palveluun.
 * @author suonpaas
 */
public class GoogleAuth {
    private Sheets sheetsService;
    private final String APPLICATION_NAME = "JumpAddiction";
    private final String SPREADSHEET_ID = "13SbeJMpXpiVgcL6kiNvRDlvb8_9t5RUZ-4LW8WBLTRU";
    
    private final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    
    private final List<String> SCOPES = Arrays.asList(SheetsScopes.SPREADSHEETS);
    
    
    /**
     * Autentikoituu Googlen spreadsheet palveluun palvelutunnuksella ja paluttaa taulukon.
     * @return Sheets muuttujana oleva taulukko. 
     * @throws GeneralSecurityException Autentikoinnissa tapahtunut virhe.
     * @throws IOException latauksessa/lähetyksessä tapahtunut virhe.
     */
    private Sheets getSheetsService() throws GeneralSecurityException, IOException {
        KeyStore keystore = KeyStore.getInstance("PKCS12");
        keystore.load(this.getClass().getClassLoader().getResourceAsStream("service_account.p12"), "notasecret".toCharArray());
        PrivateKey pk = (PrivateKey)keystore.getKey("privatekey", "notasecret".toCharArray());
        Credential credential = new GoogleCredential.Builder()
                .setTransport(GoogleNetHttpTransport.newTrustedTransport())
                .setJsonFactory(JSON_FACTORY)
                .setServiceAccountId("leaderboard@jumpaddiction-1588274964013.iam.gserviceaccount.com")
                .setServiceAccountPrivateKey(pk)
                .setServiceAccountScopes(SCOPES)
                .build();
                credential.refreshToken();
        return new Sheets.Builder(GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }
    
    /**
     * Lataa annetusta taulukosta tiedot. 
     * @param difficulty String muuttujana annettava taulukon nimi. 
     * @return palauttaa ladatut tiedostot List muuttujana. 
     * @throws GeneralSecurityException Autentikoinnissa tapahtuva virhe. 
     * @throws IOException Taulukosta tiedon luvussa tapahtunut virhe. 
     */
    public List readData(String difficulty) throws GeneralSecurityException, IOException {
        sheetsService = getSheetsService();
        String range = difficulty + "!A2:B11";
        
        ValueRange response = sheetsService.spreadsheets().values()
                .get(SPREADSHEET_ID, range)
                .execute();
        
        List<List<Object>> values = response.getValues();
        
        return values;
    }
    
    /**
     * Lisää annetun datan määriteltyyn taulukkoon. 
     * @param name String muuttujana annettu lisättävä tietue.
     * @param result int muuttujana annettu lisättävä tietue.
     * @param difficulty String muuttujana annettu taulukon nimi. 
     * @throws GeneralSecurityException Autentikoinnissa tapahtuva virhe.
     * @throws IOException Taulukkoon kirjoittamisessa tapahtuva virhe. 
     */
    public void addResult(String name, int result, String difficulty) throws GeneralSecurityException, IOException {
        sheetsService = getSheetsService();
        
        ValueRange addRow = new ValueRange()
                .setValues(Arrays.asList(
                    Arrays.asList(name, String.valueOf(result))
                ));
        
        AppendValuesResponse addResult = sheetsService.spreadsheets().values()
                .append(SPREADSHEET_ID, difficulty, addRow)
                .setValueInputOption("USER_ENTERED")
                .setInsertDataOption("INSERT_ROWS")
                .setIncludeValuesInResponse(true)
                .execute();
    }
}
