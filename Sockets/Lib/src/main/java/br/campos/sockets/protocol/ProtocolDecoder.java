package br.campos.sockets.protocol;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineDecoder;

import br.campos.sockets.model.Protocol;
import br.campos.sockets.util.Constants;

public class ProtocolDecoder extends TextLineDecoder {
	
    public ProtocolDecoder() {
        super(new LineDelimiter(Constants.byteToString(Constants.END)));
    }            
 
    @Override
    protected void writeText(IoSession session, String data, ProtocolDecoderOutput out) {
    	Protocol protocol = null;
    	try {
			protocol = Protocol.valueOf(data.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
        out.write(protocol);
    }
}
