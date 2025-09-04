package apollo.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

/**
 * Represents a chat message in the GUI of the Apollo chatbot.
 * <p>
 * Each Message consists of a speech bubble containing the message text and
 * a circular avatar image. The alignment, background color, and avatar
 * used depend on whether the message is sent by the user or Apollo.
 */
public class Message extends HBox {
    private Image userImg = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image apolloImg = new Image(this.getClass().getResourceAsStream("/images/apollo.png"));

    /**
     * Constructs a Message object containing the given text and avatar.
     * <p>
     * The message will be displayed in a speech bubble with a maximum width,
     * and the avatar will be clipped to a circle. Messages from the user are
     * aligned to the right and use the user avatar; messages from Apollo are
     * aligned to the left and use the Apollo avatar.
     *
     * @param text   The text content of the message.
     * @param isUser True if the message is sent by the user; false if sent by Apollo.
     */
    public Message(String text, boolean isUser) {
        Label label = new Label(text);
        label.setWrapText(true);
        label.setMaxWidth(280);
        label.setStyle(
                "-fx-background-color: " + (isUser ? "#cce5ff" : "#ffffff") + ";"
                        + "-fx-padding: 8;"
                        + "-fx-background-radius: 12;"
        );

        ImageView avatar = new ImageView(isUser ? userImg : apolloImg);
        avatar.setFitWidth(40);
        avatar.setFitHeight(40);
        avatar.setClip(new Circle(20, 20, 20));

        this.setSpacing(10);
        this.setPadding(new Insets(5));

        if (isUser) {
            this.setAlignment(Pos.TOP_RIGHT);
            this.getChildren().addAll(label, avatar);
        } else {
            this.setAlignment(Pos.TOP_LEFT);
            this.getChildren().addAll(avatar, label);
        }
    }
}
