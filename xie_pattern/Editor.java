package xie;

public class Editor {
    private String Content;
    private String FontName;
    private int FontSize;
    private String FontStyle;
    private String Color;

    public Editor(String Content, String FontStyle, int FontSize) {
        this.Content = Content;
        this.FontStyle = FontStyle;
        this.FontSize = FontSize;
    }

    public void setFontName(String fontName) {
        FontName = fontName;
    }

    public void setColor(String color) {
        Color = color;
    }

    public void setContent(String content) {
        Content = content;
    }

    public void setFontSize(int fontSize) {
        FontSize = fontSize;
    }

    public void setFontStyle(String fontStyle) {
        FontStyle = fontStyle;
    }

    public String getColor() {
        return Color;
    }

    public String getContent() {
        return Content;
    }

    public String getFontName() {
        return FontName;
    }

    public int getFontSize() {
        return FontSize;
    }

    public String getFontStyle() {
        return FontStyle;
    }
}
