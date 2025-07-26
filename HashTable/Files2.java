import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
/*
Input:
2
root/a/ 1.txt (abcd)
root/a/ 2.txt (efgh)
3
add root/c/d/ 4.txt (efgh)
delete root/a/ 1.txt (abcd)
find root/a/ 1.txt (abcd)
efgh
Output:
false
root/a/2.txt root/c/d 4.txt
*/

// APS book
class File {
    String path;
    String name;
    String content;

    public File(String path, String name, String content) {
        this.path = path;
        this.name = name;
        this.content = content;
    }

    @Override
    public String toString() {
        return path + name;
    }
}

public class Files2 {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(input.readLine());
        CBHT<String, ArrayList<File>> fileSystem = new CBHT<>(n * 2 - 1);

        for (int i = 0; i < n; i++) {
            String []parts = input.readLine().split(" ");
            String path = parts[0];
            String name = parts[1];
            String content = parts[2];
            File newFile = new File(path, name, content);

            SLLNode<MapEntry<String, ArrayList<File>>> node = fileSystem.search(content);
            if (node == null) {
                ArrayList<File> fileList = new ArrayList<>();
                fileList.add(newFile);
                fileSystem.insert(content, fileList);
            }
            else {
                ArrayList<File> fileList = node.element.value;
                fileList.add(newFile);
                fileSystem.insert(content, fileList);
            }
        }

        int m = Integer.parseInt(input.readLine());
        for (int i = 0; i < m; i++) {
            String []parts = input.readLine().split(" ");
            String command = parts[0];
            String path = parts[1];
            String name = parts[2];
            String content = parts[3];
            File newFile = new File(path, name, content);

            SLLNode<MapEntry<String, ArrayList<File>>> node = fileSystem.search(content);
            switch(command) {
                case "add":
                    if (node == null) {                                     // ако не постои, исто како горе при додавањетo
                        ArrayList<File> fileList = new ArrayList<>();
                        fileList.add(newFile);
                        fileSystem.insert(content, fileList);
                    }
                    else {                                                  // ако постои, ја додаваме само содржината
                        node.element.value.add(newFile);
                    }
                    break;
                case "delete":
                    if (node != null) {
                        ArrayList<File> fileList = node.element.value;
                        fileList.remove(newFile);
                        if (fileList.isEmpty()) {
                            fileSystem.delete(content);
                        } else {
                            fileSystem.insert(content, fileList);
                        }
                    }
                    break;
                case "find":
                    boolean found = node != null && node.element.value.contains(newFile);
                    System.out.println(found ? "true" : "false");
                    break;
            }
        }
        String checkContent = input.readLine();
        checkContent = "(" + checkContent + ")";
        SLLNode<MapEntry<String, ArrayList<File>>> result = fileSystem.search(checkContent);
        if (result != null) {
            for (File f : result.element.value) {
                System.out.println(f + " ");
            }
        }
    }
}
