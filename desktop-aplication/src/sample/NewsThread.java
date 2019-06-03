package sample;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class NewsThread implements Runnable {

    private Parent root;

    public NewsThread(Parent root) {
        this.root = root;
    }

    @Override
    public void run() {
        Queue<News> newsQueue = getNews();

        while (true) {
            try {
                TimeUnit.SECONDS.sleep(3);

                News message = newsQueue.remove();
                Platform.runLater(new NewsChanger(root,message.getText()));
                newsQueue.add(message);
                System.out.println(message.getText());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Queue<News> getNews() {
        Queue<News> news = new LinkedList<>();

        News news1 = new News();
        news1.setDate("Date1");
        news1.setName("News1");
        news1.setNewsId(1);
        news1.setTime(3);
        news1.setText("Dupa i kamieni kupa");
        news.add(news1);

        News news2 = new News();
        news2.setDate("Date2");
        news2.setName("News2");
        news2.setNewsId(2);
        news2.setTime(3);
        news2.setText("Druga wiadomosc na pasku");
        news.add(news2);

        News news3 = new News();
        news3.setDate("Date3");
        news3.setName("News3");
        news3.setNewsId(3);
        news3.setTime(4);
        news3.setText("Trzecia wiadomosc niezwykle wazna");
        news.add(news3);

        return news;
    }
}
