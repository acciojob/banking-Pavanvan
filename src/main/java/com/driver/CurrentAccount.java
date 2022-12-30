package com.driver;

public class CurrentAccount extends BankAccount {
    private String tradeLicenseId;

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        super(name, balance, 5000);
        this.tradeLicenseId = tradeLicenseId;
        if (balance < 5000) {
            throw new Exception("Insufficient Balance");
        }
        validateLicenseId();
    }

    private void validateLicenseId() throws Exception {
        if (isValidLicenseId(tradeLicenseId)) {
            return;
        }
        String rearrangedId = rearrangeString(tradeLicenseId);
        if (rearrangedId.isEmpty()) {
            throw new Exception("Valid License can not be generated");
        }
        this.tradeLicenseId = rearrangedId;
    }

    private boolean isValidLicenseId(String licenseId) {
        for (int i = 0; i < licenseId.length() - 1; i++) {
            if (licenseId.charAt(i) == licenseId.charAt(i + 1)) {
                return false;
            }
        }
        return true;
    }

    private String rearrangeString(String s) {
        int n = s.length();
        int[] count = new int[26];
        for (int i = 0; i < 26; i++) {
            count[i] = 0;
        }
        for (char ch : s.toCharArray()) {
            count[ch - 'A']++;
        }

        char maxChar = getMaxChar(count);
        int maxCount = count[maxChar - 'A'];

        if (maxCount > (n + 1) / 2) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(' ');
        }

        int index = 0;
        while (maxCount > 0) {
            sb.setCharAt(index, maxChar);
            index += 2;
            maxCount--;
        }
        count[maxChar - 'A'] = 0;
        for (int i = 0; i < 26; i++) {
            while (count[i] > 0) {
                index = (index >= n) ? 1 : index;
                sb.setCharAt(index, (char)('A' + i));
                index += 2;
                count[i]--;
            }
        }
        return sb.toString();
    }

    private char getMaxChar(int[] count) {
        int max = 0;
        char ch = 0;
        for (int i = 0; i < 26; i++) {
            if (count[i] > max) {
                max = count[i];
                ch = (char)('A' + i);
            }
        }
        return ch;
    }

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }
}
