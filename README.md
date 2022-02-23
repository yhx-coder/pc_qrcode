# pc_qrcode 小工具
Scan the QR code in the web page on the PC.<br>
先通过 Robot 将带有二维码的网页截图保存，然后利用 zxing 库进行二维码解码。<br>
输入为带有二维码的网址，输出为二维码的含义字符串。入口类忘写了，目前在 test 里。<br>
必须要打开网页才能截图，不是一个好方法，以后想着改进吧。
