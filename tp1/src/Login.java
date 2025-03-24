import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Scanner;

public class Login {
    public static void main(String[] args) {
        HashMap<String, String> userDatabase = loadUserDatabase("../data/user_hashpwd.csv");
        Scanner scanner = new Scanner(System.in);

        while (true) {

            // Code here: 2.5 Système de connexion

            System.out.print("Entrez nom d'utilisateur: ");
            String username = scanner.nextLine();

            if (!userDatabase.containsKey(username)){
                System.out.println("Utilisateur non trouvé. Réessayez!");
                continue;
            }

            int attempts = 3;
            while (attempts>0) {
                System.out.print("Entrez le mot de passe: ");
                String password = scanner.nextLine();
                String passwordImputed = hashPassword(password);
                String passwordInDataBase = userDatabase.get(username);

                if (passwordImputed.equals(passwordInDataBase)){
                    System.out.println("Connexion réussie!");
                    scanner.close();
                    break;
                }
                else {
                    attempts--;
                    if (attempts >0){
                        System.out.println("Mot de passe incorrect. " + attempts + "tentatives restantes");
                    }
                    else {
                        System.out.println("Mot de passe incorrect. Réessayez!");
                    }       
                }  
            }
        }
    }

    /**
     * Loads a user database from a CSV file.
     * The CSV file is expected to have two columns: username and hashed password.
     * 
     * @param filename The path to the CSV file containing user data.
     * @return A HashMap where keys are usernames and values are hashed passwords.
     * 
     * @throws IOException If an error occurs while reading the file.
     */
    public static HashMap<String, String> loadUserDatabase(String filename) {
        HashMap<String, String> userDatabase = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    userDatabase.put(parts[0].trim(), parts[1].trim());
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
        return userDatabase;
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());

            StringBuilder hexString = new StringBuilder();

            for (byte b : hashedBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
}
