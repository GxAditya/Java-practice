package Practice;    
public class test {
    public static void main(String[] args) {
        int n = 3; // Number of rows in the pattern

        // Loop through each row
        for (int i = 1; i <= n; i++) {
            // Print leading spaces
            for (int j = i; j < n; j++) {
                System.out.print(" ");
            }

            // Print stars
            for (int k = 1; k <= (2 * i - 1); k++) {
                System.out.print("*");
            }

            // Move to the next line after each row
            System.out.println();
        }
    }
}

//This is for following pattern:
//  *
// ***
//*****