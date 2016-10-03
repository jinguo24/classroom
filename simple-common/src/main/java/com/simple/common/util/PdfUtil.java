package com.simple.common.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.Pipeline;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.exceptions.CssResolverException;
import com.itextpdf.tool.xml.html.CssAppliers;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
import com.simple.common.excel.ExcelUtil;

public class PdfUtil {

	private static final String PDF_DIR = "/temp/download/";
	private static class MyFontsProvider extends XMLWorkerFontProvider{
        public MyFontsProvider(){
            super(null,null);
        }
        @Override
        public Font getFont(final String fontname, String encoding, float size, final int style) {
                                                                                               
            String fntname = fontname;
            if(fntname==null){
                fntname="宋体";
            }
            return super.getFont(fntname, encoding, size, style);
        }
    }
                                                                                           
    private static void convert2(InputStream infile, String outfile)
            throws FileNotFoundException, IOException, DocumentException,
            CssResolverException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document,
                new FileOutputStream(outfile));
        document.open();
        MyFontsProvider fontProvider = new MyFontsProvider();
        fontProvider.addFontSubstitute("lowagie", "garamond");
        fontProvider.setUseUnicode(true);
        //使用我们的字体提供器，并将其设置为unicode字体样式
        CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);
        HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);
        htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
        CSSResolver cssResolver = XMLWorkerHelper.getInstance()
                .getDefaultCssResolver(true);
        Pipeline<?> pipeline = new CssResolverPipeline(cssResolver,
                new HtmlPipeline(htmlContext, new PdfWriterPipeline(document,
                        writer)));
        XMLWorker worker = new XMLWorker(pipeline, true);
        XMLParser p = new XMLParser(worker);
        //File input = new File(infile);
        //p.parse(new InputStreamReader(new FileInputStream(input), "UTF-8"));
        p.parse(new InputStreamReader(infile, "UTF-8"));
        document.close();
    }

    public static String download(String html) {
    	ByteArrayInputStream stream = null;
    	try {
			stream = new ByteArrayInputStream(html.getBytes());
			String filefolder = PDF_DIR+System.currentTimeMillis();
			String filename = PrimaryKeyUtil.getUUID()+".pdf";
			File file = new File(filefolder);
			if (!file.exists()) {
				file.mkdirs();
			}
			
			File destionFile = new File(filefolder+"/"+filename);
			if (!destionFile.exists()) {
				destionFile.createNewFile();
			}
			convert2(stream,filefolder+"/"+filename);
			return filefolder+"/"+filename;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (CssResolverException e) {
			e.printStackTrace();
		}finally {
			try {
				if ( null != stream ) {
					stream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    	return null;
    }
    
    public static void main(String[] args) {
    	String s ="<div class=\"list_name\">                                                                                                                                                                                                                                      "+
				"					<a href=\"http://www.lenovo.com.cn/lenovoindex.html\"  class=\"list_nm\" style=\"height:6.5px;line-height:6.5px\" latag=\"latag_pc_common_navcategory_1_Lenovo 电脑\" >Lenovo 电脑                                                                    "+
				"						<span class=\"list_usepng list_icona\"></span>                                                                                                                                                                                                      "+
				"					</a>                                                                                                                                                                                                                                                  "+
				"						<div class=\"list_cont\">                                                                                                                                                                                                                           "+
				"							<div class=\"list_lt\">                                                                                                                                                                                                                           "+
				"								<div class=\"list_lta\">                                                                                                                                                                                                                        "+
				"									<p><a href=\"http://s.lenovo.com.cn/?index=0&fromhomepage\"  latag=\"latag_pc_common_navcategory_11_笔记本\" >笔记本</a></p>                                                                                                                  "+
				"									<ul class=\"clearfix\">                                                                                                                                                                                                                       "+
				"										<li><a href=\"http://s.lenovo.com.cn/?index=0&239=011406,011410,011405,011404,011411,011412,011415,011413,011414,011417,011416&fromhomepage\"  latag=\"latag_pc_common_navcategory_111_常规笔记本\" >常规笔记本</a></li>                    "+
				"										<li><a href=\"http://s.lenovo.com.cn/?index=0&239=011404,011402,01140507,012101,011411,011412,01140510,01140512,011413,011415&fromhomepage\"  latag=\"latag_pc_common_navcategory_112_超轻薄笔记本\" >超轻薄笔记本</a></li>                 "+
				"										<li><a href=\"http://s.lenovo.com.cn/?index=0&239=011407,011405&fromhomepage\"  latag=\"latag_pc_common_navcategory_113_互联网爆款\" >互联网爆款</a></li>                                                                                   "+
				"										<li><a href=\"http://s.lenovo.com.cn/?index=0&239=011403,011407,01140501,01140509&fromhomepage\"  latag=\"latag_pc_common_navcategory_114_游戏本\" >游戏本</a></li>                                                                         "+
				"										<li><a href=\"http://s.lenovo.com.cn/?index=0&239=012101,012102,012103&fromhomepage\"  latag=\"latag_pc_common_navcategory_115_变形笔记本\" >变形笔记本</a></li>                                                                            "+
				"									</ul>                                                                                                                                                                                                                                         "+
				"								</div>                                                                                                                                                                                                                                          "+
				"								<div class=\"list_lta\">                                                                                                                                                                                                                        "+
				"									<p><a href=\"http://s.lenovo.com.cn/?index=0&fromhomepage\"  latag=\"latag_pc_common_navcategory_12_笔记本场景\" >笔记本场景</a></p>                                                                                                          "+
				"									<ul class=\"clearfix\">                                                                                                                                                                                                                       "+
				"										<li><a href=\"http://s.lenovo.com.cn/?index=0&246=校园学生&fromhomepage\"  latag=\"latag_pc_common_navcategory_121_校园学生\" >校园学生</a></li>                                                                                            "+
				"										<li><a href=\"http://s.lenovo.com.cn/?index=0&246=时尚女生&fromhomepage\"  latag=\"latag_pc_common_navcategory_122_时尚女生\" >时尚女生</a></li>                                                                                            "+
				"										<li><a href=\"http://s.lenovo.com.cn/?index=0&246=家庭娱乐&fromhomepage\"  latag=\"latag_pc_common_navcategory_123_家庭娱乐\" >家庭娱乐</a></li>                                                                                            "+
				"										<li><a href=\"http://s.lenovo.com.cn/?index=0&246=商务办公&fromhomepage\"  latag=\"latag_pc_common_navcategory_124_商务办公\" >商务办公</a></li>                                                                                            "+
				"										<li><a href=\"http://s.lenovo.com.cn/?index=0&246=高清游戏&fromhomepage\"  latag=\"latag_pc_common_navcategory_125_高清游戏\" >高清游戏</a></li>                                                                                            "+
				"										<li><a href=\"http://s.lenovo.com.cn/?index=0&246=轻薄便携&fromhomepage\"  latag=\"latag_pc_common_navcategory_126_轻薄便携\" >轻薄便携</a></li>                                                                                            "+
				"									</ul>                                                                                                                                                                                                                                         "+
				"								</div>                                                                                                                                                                                                                                          "+
				"								<div class=\"list_lta\">                                                                                                                                                                                                                        "+
				"									<p><a href=\"http://s.lenovo.com.cn/?index=2&fromhomepage\"  latag=\"latag_pc_common_navcategory_13_台式机\" >台式机</a></p>                                                                                                                  "+
				"									<ul class=\"clearfix\">                                                                                                                                                                                                                       "+
				"										<li><a href=\"http://s.lenovo.com.cn/?index=2&320=0116&fromhomepage\"  latag=\"latag_pc_common_navcategory_131_一体台式机\" >一体台式机</a></li>                                                                                            "+
				"										<li><a href=\"http://s.lenovo.com.cn/?index=2&320=0113,0101&fromhomepage\"  latag=\"latag_pc_common_navcategory_132_分体台式机\" >分体台式机</a></li>                                                                                       "+
				"									</ul>                                                                                                                                                                                                                                         "+
				"								</div>                                                                                                                                                                                                                                          "+
				"								<div class=\"list_lta\">                                                                                                                                                                                                                        "+
				"									<p><a href=\"http://s.lenovo.com.cn/?index=2&fromhomepage\"  latag=\"latag_pc_common_navcategory_14_台式机场景\" >台式机场景</a></p>                                                                                                          "+
				"									<ul class=\"clearfix\">                                                                                                                                                                                                                       "+
				"										<li><a href=\"http://s.lenovo.com.cn/?index=2&322=金榜题名&fromhomepage\"  latag=\"latag_pc_common_navcategory_141_金榜题名\" >金榜题名</a></li>                                                                                            "+
				"										<li><a href=\"http://s.lenovo.com.cn/?index=2&322=疯狂游戏&fromhomepage\"  latag=\"latag_pc_common_navcategory_142_疯狂游戏\" >疯狂游戏</a></li>                                                                                            "+
				"										<li><a href=\"http://s.lenovo.com.cn/?index=2&322=致美大屏&fromhomepage\"  latag=\"latag_pc_common_navcategory_143_致美大屏\" >致美大屏</a></li>                                                                                            "+
				"										<li><a href=\"http://s.lenovo.com.cn/?index=2&322=家庭娱乐&fromhomepage\"  latag=\"latag_pc_common_navcategory_144_家庭娱乐\" >家庭娱乐</a></li>                                                                                            "+
				"										<li><a href=\"http://s.lenovo.com.cn/?index=2&322=商务办公&fromhomepage\"  latag=\"latag_pc_common_navcategory_145_商务办公\" >商务办公</a></li>                                                                                            "+
				"										<li><a href=\"http://s.lenovo.com.cn/?index=2&322=随心触控&fromhomepage\"  latag=\"latag_pc_common_navcategory_146_随心触控\" >随心触控</a></li>                                                                                            "+
				"									</ul>                                                                                                                                                                                                                                         "+
				"								</div>                                                                                                                                                                                                                                          "+
				"							</div>                                                                                                                                                                                                                                            "+
				"							                                                                                                                                                                                                                                                  "+
				"							<div class=\"list_rt\">                                                                                                                                                                                                                           "+
				"								<a href=\"http://www.lenovo.com.cn/product/51823.html\"><img class=\"classification_img\"  /></a>                  "+
				"								<a href=\"http://s.lenovo.com.cn/?index=2&320=0116&fromhomepage\"><img class=\"classification_img\" /></a>        "+
				"							</div>                                                                                                                                                                                                                                            "+
				"						</div>                                                                                                                                                                                                                                              "+
				"				</div>  "; 
    	download(s);
	}
}
