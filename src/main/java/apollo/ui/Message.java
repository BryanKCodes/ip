package apollo.ui;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

/**
 * Represents a single chat message in the GUI.
 * Each message consists of an avatar, a speech bubble, and a timestamp.
 * The styling is controlled by an external CSS file (styles.css).
 */
public class Message extends VBox {

    private final Image userImg = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image apolloImg = new Image(this.getClass().getResourceAsStream("/images/apollo.png"));

    /**
     * Constructs a Message UI component.
     *
     * @param text The text content of the message.
     * @param isUser True if the message is from the user, false if from Apollo.
     */
    public Message(String text, boolean isUser) {
        super(5); // Spacing for the VBox

        // Create the message text bubble
        Label label = new Label(text);
        label.setWrapText(true);
        label.getStyleClass().add("message-bubble");

        // Create the avatar image
        ImageView avatar = new ImageView(isUser ? userImg : apolloImg);
        avatar.setFitWidth(40);
        avatar.setFitHeight(40);
        avatar.setClip(new Circle(20, 20, 20));

        // Create the timestamp
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("h:mm a"));
        Label timestampLabel = new Label(time);
        timestampLabel.getStyleClass().add("timestamp-label");

        // HBox to hold the message bubble and avatar
        HBox messageBody = new HBox(10);

        // VBox to hold the message bubble and timestamp
        VBox messageContent = new VBox(2);
        messageContent.getChildren().addAll(label, timestampLabel);

        if (isUser) {
            this.getStyleClass().add("user-message");
            messageBody.setAlignment(Pos.TOP_RIGHT);
            messageBody.getChildren().addAll(messageContent, avatar);
            messageContent.setAlignment(Pos.BOTTOM_RIGHT);
            this.setAlignment(Pos.TOP_RIGHT);
        } else {
            this.getStyleClass().add("apollo-message");
            messageBody.setAlignment(Pos.TOP_LEFT);
            messageBody.getChildren().addAll(avatar, messageContent);
            messageContent.setAlignment(Pos.BOTTOM_LEFT);
            this.setAlignment(Pos.TOP_LEFT);
        }

        this.setPadding(new Insets(5, 10, 5, 10));
        this.getChildren().add(messageBody);
    }
}
