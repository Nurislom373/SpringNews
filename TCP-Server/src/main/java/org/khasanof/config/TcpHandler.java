package org.khasanof.config;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @author Nurislom
 * @see org.khasanof.config
 * @since 5/4/26
 */
@Component
public class TcpHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("RAW HEX: " + msg);
        try {
            byte[] data = hexToBytes(msg);

            if (data[0] != 0x7E || data[data.length - 1] != 0x0D) {
                System.out.println("Invalid frame");
                return;
            }

            int len = ((data[1] & 0xFF) << 8) | (data[2] & 0xFF);
            byte cmd = data[3];
            byte seq = data[4];

            byte[] payload = Arrays.copyOfRange(data, 5, 5 + len - 2);
            byte crc = data[data.length - 2];

            if (!validateCrc(data, crc)) {
                System.out.println("CRC error");
                return;
            }

            handleCommand(ctx, cmd, seq, payload);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleCommand(ChannelHandlerContext ctx, byte cmd, byte seq, byte[] payload) {
        switch (cmd) {
            case 0x01 -> handleAuth(ctx, seq, payload);
            case 0x03 -> handleHeartbeat(ctx, seq);
            case 0x04 -> handleUnlock(ctx, seq, payload);
            default -> System.out.println("Unknown CMD: " + cmd);
        }
    }

    private void handleAuth(ChannelHandlerContext ctx, byte seq, byte[] payload) {
        String imei = new String(payload, StandardCharsets.UTF_8);
        System.out.println("AUTH IMEI: " + imei);
        sendResponse(ctx, (byte) 0x02, seq, new byte[]{0x00}); // success
    }

    private void handleHeartbeat(ChannelHandlerContext ctx, byte seq) {
        System.out.println("Heartbeat received");
        sendResponse(ctx, (byte) 0x03, seq, new byte[]{});
    }

    private void handleUnlock(ChannelHandlerContext ctx, byte seq, byte[] payload) {
        String password = new String(payload, StandardCharsets.UTF_8);
        System.out.println("Unlock request: " + password);
        sendResponse(ctx, (byte) 0x05, seq, new byte[]{0x00});
    }

    private void sendResponse(ChannelHandlerContext ctx, byte cmd, byte seq, byte[] payload) {
        int len = 2 + payload.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        out.write(0x7E);
        out.write((len >> 8) & 0xFF);
        out.write(len & 0xFF);
        out.write(cmd);
        out.write(seq);

        out.writeBytes(payload);

        byte crc = calculateCrc(out.toByteArray(), 1);
        out.write(crc);
        out.write(0x0D);

        String hex = bytesToHex(out.toByteArray());
        ctx.writeAndFlush(hex);
    }

    // ===== Utils =====

    private byte[] hexToBytes(String hex) {
        int len = hex.length();
        byte[] data = new byte[len / 2];

        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                            + Character.digit(hex.charAt(i + 1), 16));
        }
        return data;
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }

    private boolean validateCrc(byte[] data, byte crc) {
        byte calc = calculateCrc(data, 1);
        return calc == crc;
    }

    private byte calculateCrc(byte[] data, int startIndex) {
        byte crc = 0x00;
        for (int i = startIndex; i < data.length - 2; i++) {
            crc ^= data[i];
        }
        return crc;
    }
}
