package cn.hengxin.paper.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBody;

public class POIMergeDocUtils {
	public static void mergeDoc(String[] srcDocxs,String destDocx){
		
		OutputStream dest = null;
		List<OPCPackage> opcpList = new ArrayList<OPCPackage>();
		int length = null == srcDocxs ? 0 : srcDocxs.length;

		for (int i = 0; i < length; i++) {
			String doc = srcDocxs[i];
			OPCPackage srcPackage =  null;
			try {
				srcPackage = OPCPackage.open(doc);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(null != srcPackage){
				opcpList.add(srcPackage);
			}
		}
		
		int opcpSize = opcpList.size();
		//��ȡ��OPCPackage�������0ʱ��ִ�кϲ�����
		if(opcpSize > 0){
			try {
				dest = new FileOutputStream(destDocx);
				XWPFDocument src1Document = new XWPFDocument(opcpList.get(0));
				CTBody src1Body = src1Document.getDocument().getBody();
				//OPCPackage����1�Ĳ���ִ�кϲ�����
				if(opcpSize > 1){
					for (int i = 1; i < opcpSize; i++) {
						OPCPackage src2Package = opcpList.get(i);
						XWPFDocument src2Document = new XWPFDocument(src2Package);
						CTBody src2Body = src2Document.getDocument().getBody();
						appendBody(src1Body, src2Body);
					}
				}
				//���ϲ����ĵ�д��Ŀ���ļ���
				src1Document.write(dest);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
                //ע�͵����²��֣�ȥ��Ӱ��Ŀ���ļ�srcDocxs��
				/*for (OPCPackage opcPackage : opcpList) {
					if(null != opcPackage){
						try {
							opcPackage.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}*/
				//�ر���
				IOUtils.closeQuietly(dest);
			}
		}
	}

	private static void appendBody(CTBody src, CTBody append) throws XmlException {
		XmlOptions optionsOuter = new XmlOptions();
		optionsOuter.setSaveOuter();
		String appendString = append.xmlText(optionsOuter);
		String srcString = src.xmlText();
		String prefix = srcString.substring(0, srcString.indexOf(">") + 1);
		String mainPart = srcString.substring(srcString.indexOf(">") + 1,
				srcString.lastIndexOf("<"));
		String sufix = srcString.substring(srcString.lastIndexOf("<"));
		String addPart = appendString.substring(appendString.indexOf(">") + 1,
				appendString.lastIndexOf("<"));
		CTBody makeBody = CTBody.Factory.parse(prefix + mainPart + addPart
				+ sufix);
		src.set(makeBody);
	}

}
