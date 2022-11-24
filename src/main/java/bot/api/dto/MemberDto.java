package bot.api.dto;

public class MemberDto {
    private String name;
    private String id;
    private String inVoice;

    public MemberDto(String name, String id, String inVoice) {
        this.name = name;
        this.id = id;
        this.inVoice = inVoice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInVoice() {
        return inVoice;
    }

    public void setInVoice(String inVoice) {
        this.inVoice = inVoice;
    }
}
