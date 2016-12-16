<%@ page contentType="image/jpeg"
         import="com.hc.core.utils.RandomUtils,javax.imageio.ImageIO,java.awt.*,java.awt.image.BufferedImage,java.util.Random"
         pageEncoding="utf-8" %>
<%!
    //随机获取图片颜色
    Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) fc = 255;
        if (bc > 255) bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
%>
<%
    //response设置
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);

//设置图片大小
    int width = 60, height = 28;
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

//Graphics图片对象
    Graphics g = image.getGraphics();

    Random random = new Random();

    g.setColor(getRandColor(200, 250));
    g.fillRect(0, 0, width, height);

    g.setFont(new Font("Times New Roman", Font.PLAIN, 18));

// 设置图片中的随机数
    g.setColor(getRandColor(160, 200));
/* //混杂线条
    for (int i = 0; i < 155; i++) {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(12);
        int yl = random.nextInt(12);
        g.drawLine(x, y, x + xl, y + yl);
    }*/

    String sRand = ""+random.nextInt(10)+random.nextInt(10)+random.nextInt(10)+random.nextInt(10);
    //String sRand = RandomUtils.generateClearString(4);
    for (int i = 0; i < 4; i++) {
        String rand = String.valueOf(random.nextInt(10));
//    sRand+=rand;
        g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
        g.drawString(String.valueOf(sRand.charAt(i)), 13 * i + 6, 16);
    }

// 把随值数放入SESSION
    session.setAttribute("VALIDATION_CODE", sRand);

    g.dispose();

    ImageIO.write(image, "JPEG", response.getOutputStream());
    out.clear();
    out = pageContext.pushBody();
%>