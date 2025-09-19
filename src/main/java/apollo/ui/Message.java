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
    private static final String USER_IMG_PATH = "/images/user.png";
    private static final String APOLLO_IMG_PATH = "/images/apollo.png";
    private static final Image USER_IMG = new Image(Message.class.getResourceAsStream(USER_IMG_PATH));
    private static final Image APOLLO_IMG = new Image(Message.class.getResourceAsStream(APOLLO_IMG_PATH));

    private static final double AVATAR_SIZE = 40.0;
    private static final double VBOX_SPACING = 5.0;
    private static final double HBOX_SPACING = 10.0;
    private static final Insets MESSAGE_PADDING = new Insets(5, 10, 5, 10);

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
        ImageView avatar = new ImageView(isUser ? USER_IMG : APOLLO_IMG);
        avatar.setFitWidth(AVATAR_SIZE);
        avatar.setFitHeight(AVATAR_SIZE);
        avatar.setClip(new Circle(AVATAR_SIZE / 2, AVATAR_SIZE / 2, AVATAR_SIZE / 2));

        // Create the timestamp
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("h:mm a"));
        Label timestampLabel = new Label(time);
        timestampLabel.getStyleClass().add("timestamp-label");

        // HBox to hold the message bubble and avatar
        HBox messageBody = new HBox(HBOX_SPACING);

        // VBox to hold the message bubble and timestamp
        VBox messageContent = new VBox(VBOX_SPACING);
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

        this.setPadding(MESSAGE_PADDING);
        this.getChildren().add(messageBody);
    }
}
