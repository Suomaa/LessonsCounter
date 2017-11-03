import java.util.UUID;

public class Lesson {

    private UUID mId;
    private String mTitle;

    public Lesson() {
        //Generate a unique ID
        mId = UUID.randomUUID();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

}
