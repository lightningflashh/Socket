import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class MyURL {
    public static void main(String[] args) {
        try {
            String urlString = "https://vi.wikipedia.org/wiki/Trang_Ch%C3%ADnh";
            URL url = new URL(urlString);

            InputStreamReader is = new InputStreamReader(url.openStream());
            BufferedReader br = new BufferedReader(is);
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
