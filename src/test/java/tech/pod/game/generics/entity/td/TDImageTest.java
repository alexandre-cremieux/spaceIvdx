package tech.pod.game.generics.entity.td;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tech.pod.game.generics.ui.graphics.Color;
import tech.pod.game.generics.ui.graphics.RGBAColor;
import tech.pod.game.generics.ui.graphics.TDImage;

class TDImageTest
{
    private final Color blank = new RGBAColor(0, 0, 0, 0);

    /**
     * Define an image with it's background
     * @param height The image's height
     * @param width The image's width
     * @param backGround The image's background
     * @param <C> The Color
     * @return A new TDImage
     */
    public static <C extends Color> TDImage<C> initImage(int width, int height, C backGround) {
        return new TDImage<>(width, height, backGround) {};
    }

    /**
     * Define a new color.
     * @return A newly created color
     */
    public static Color generateColor() {
        return new Color() {};
    }

    @Test
    @DisplayName("Test TD image size")
    void size()
    {
        TDImage<Color> image = initImage(1024, 512, this.blank);
        Assertions.assertEquals(1024 * 512, image.size);
    }

    @Test
    @DisplayName("Test TD image height")
    void height()
    {
        TDImage<Color> image = initImage(1024, 512, this.blank);
        Assertions.assertEquals(512, image.getHeight());
    }

    @Test
    @DisplayName("Test TD image width")
    void width()
    {
        TDImage<Color> image = initImage(1024, 512, this.blank);
        Assertions.assertEquals(1024, image.getWidth());
    }

    @Test
    @DisplayName("Test TD image set color")
    void setColor()
    {
        TDImage<Color> image = initImage(1024, 512, this.blank);

        Color color = generateColor();
        Assertions.assertEquals(image, image.setColor(10, 20, color));
        Assertions.assertEquals(color, image.getColor(10, 20).get());

        var idx = 20 * 1024 + 10;
        Assertions.assertEquals(color, image.getColor(idx));

        Color otherColor = generateColor();
        Assertions.assertNotEquals(otherColor,image.getColor(idx));
        Assertions.assertEquals(image, image.setColor(idx, otherColor));
    }

    @Test
    @DisplayName("Test TD image static method computeIndex")
    void computeIndex()
    {
        // x = 10, y = 20
        Assertions.assertEquals(20 * 1024 + 10, TDImage.computeIndex(10, 20, 1024, 512));
        Assertions.assertEquals(0, TDImage.computeIndex(0, 0, 1024, 512));
        Assertions.assertEquals(1024, TDImage.computeIndex(0, 1, 1024, 512));
        Assertions.assertEquals((1024 * 2) - 1, TDImage.computeIndex(1023, 1, 1024, 512));
        Assertions.assertEquals(2048, TDImage.computeIndex(0, 2, 1024, 512));
        Assertions.assertThrows(IllegalArgumentException.class, () -> TDImage.computeIndex(10, 512, 1024, 512));
        Assertions.assertThrows(IllegalArgumentException.class, () -> TDImage.computeIndex(1024, 20, 1024, 512));
    }
}