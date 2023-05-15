package com.philips.dmis.swt.ui;

import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.widgets.Code;

public class HL7Lib extends Code {
    public static final String FORMAT_DATE = "formatDate";
    public static final String FORMAT_AGE = "formatAge";
    public static final String FORMAT_CODED_VALUE = "formatCode";
    public static final String FORMAT_AUTHOR = "formatAuthor";

    public static final String PARSE_DATA = "parseData";
    public static final String PARSE_ST = "parseST";
    public static final String PARSE_ID = "parseID";
    public static final String PARSE_DTM = "parseDTM";
    public static final String PARSE_FN = "parseFN";
    public static final String PARSE_CN = "parseCN";
    public static final String PARSE_XCN = "parseXCN";
    public static final String PARSE_XTN = "parseXTN";

    public HL7Lib() {
        super("HL7");
    }

    @Override
    protected void build() {
        add(createFunction(FORMAT_DATE, JsType.STRING,
                "(hl7Date)=>{"
                        + "if(hl7Date==undefined||hl7Date==null||hl7Date==''){"
                        //+ "console.log('HL7,parseDate',hl7Date);"
                        + "return '';"
                        + "};"
                        + String.format("const d=%s(hl7Date);", PARSE_DTM)
                        + "const dd=new Date(d.value);"
                        + "const dateTimeFormat={dateStyle:'medium'};"
                        + "switch(d.precision){"
                        + "case 1:return '' + d.year;"
                        + "case 2:"
                        + "case 3:break;"
                        + "case 4:dateTimeFormat.timeStyle='short';break;"
                        + "case 5:"
                        + "case 6:dateTimeFormat.timeStyle='medium';break;"
                        + "};"
                        + "return new Intl.DateTimeFormat([],dateTimeFormat).format(dd);"
                        + "};",
                JsParameter.getInstance("hl7Date", JsType.STRING))
        );

        add(createFunction(FORMAT_AGE, JsType.NUMBER,
                "(hl7Date)=>{"
                        + "if(hl7Date==undefined||hl7Date==null||hl7Date==''){"
                        + "return 0;"
                        + "};"
                        + String.format("const d=%s(hl7Date);", PARSE_DTM)
                        + "return Math.floor((new Date().getTime()-d.value) / 31536000000);"
                        + "};",
                JsParameter.getInstance("hl7Date", JsType.STRING))
        );

        add(createFunction(FORMAT_CODED_VALUE, JsType.STRING,
                //displayName,value,codingScheme
                "(codes)=>{"
                        + "if(codes==undefined||codes==null){"
                        //+ "console.log('HL7,formatCode',code);"
                        + "return '';"
                        + "};"
                        + "const c=Array.isArray(codes)?codes:[codes];"
                        + "var s='';"
                        + "for(var i=0;i<c.length;i++){" // for
                        + "if(i>0){s+=', ';};"
                        + "s+=c[i].displayName;"
                        + "};" // end for
                        + "return s;"
                        + "};",
                JsParameter.getInstance("codes", JsType.OBJECT))
        );

        add(createFunction(FORMAT_AUTHOR, JsType.STRING,
                //person("S0183^de Groot&de&Groot^Maria"),roles[],institutions[]
                "(authors)=>{"
                        + "if(authors==undefined||authors==null){"
                        //+ "console.log('HL7,formatAuthor',authors);"
                        + "return '';"
                        + "};"
                        + "const c=Array.isArray(authors)?authors:[authors];"
                        + "var s='';"
                        + "for(var i=0;i<c.length;i++){" // for

                        + "console.log('author:',c[i]);"

                        + "if(i>0){s+=', ';};"
                        + "const xcn=parseXCN(c[i].person);"
                        + "s+=xcn[2].value[1].value;"
                        + "if(xcn[3].value!=''){"
                        + "s+=', '+xcn[3].value"
                        + "};"

                        + "var r=c[i].roles.join(', ');"
                        + "if(r!=''){"
                        + "s+=' ('+r;"

                        + "var u=c[i].institutions.join(', ');"
                        + "if(u!=''){"
                        + "s+=' at '+u;"
                        + "};"

                        + "s+=')';"
                        + "};"

                        + "};" // end for
                        + "return s;"
                        + "};",
                JsParameter.getInstance("authors", JsType.OBJECT))
        );


        // parser

        add(createPrivateFunction(PARSE_DATA, JsType.OBJECT,
                "(obj,strings)=>{"
                        + "if(strings==undefined||strings==null||false==Array.isArray(strings)){"
                        + "return null;"
                        + "};"
                        + "Object.keys(obj).forEach((key)=>{"
                        + "obj[key].value=obj[key].parse(key<=strings.length?strings[key-1]:null);}"
                        + ");"
                        + "return obj;"
                        + "};",
                JsParameter.getInstance("obj", JsType.OBJECT),
                JsParameter.getInstance("strings", JsType.ARRAY))
        );


        add(createFunction(PARSE_ST, JsType.STRING,
                "(text)=>{"
                        + "if(text==undefined||text==null){"
                        + "return '';"
                        + "};"
                        + "return text.toString();"
                        + "};",
                JsParameter.getInstance("text", JsType.STRING))
        );

        // note: ID is implemented like ST but values are part of a table
        add(createFunction(PARSE_ID, JsType.STRING,
                "(text)=>{"
                        + "if(text==undefined||text==null){"
                        + "return '';"
                        + "};"
                        + "return text.toString();"
                        + "};",
                JsParameter.getInstance("text", JsType.STRING))
        );

        add(createFunction(PARSE_DTM, JsType.OBJECT,
                // YYYY[MM[DD[HHMM[SS[.S[S[S[S]]]]]]]][+/-ZZZZ]
                "(text)=>{"
                        + "var d={value:0,year:0,month:0,date:0,hour:0,minute:0,second:0,millis:0,tz:0,precision:0};"
                        + "var s=text;"
                        + "const f={"
                        + "y:{l:4,fn:(v,d)=>{d.precision=1;d.year=parseInt(v);}},"
                        + "m:{l:2,fn:(v,d)=>{d.precision=2;d.month=parseInt(v)-1;}},"
                        + "d:{l:2,fn:(v,d)=>{d.precision=3;d.date=parseInt(v);}},"
                        + "hm:{l:4,fn:(v,d)=>{d.precision=4;d.hour=parseInt(v.substring(0,2));d.minute=parseInt(v.substring(2,4));}},"
                        + "s:{l:2,fn:(v,d)=>{d.precision=5;d.second=parseInt(v);}},"
                        + "dot:{l:1,fn:(v,d)=>{}},"
                        + "ms:{l:4,fn:(v,d)=>{precision=6;d.millis=parseInt(v);}}"
                        + "};"
                        // note: remove timezone
                        + "var timeOffset=0;"
                        + "if(s.indexOf('-')!=-1){"
                        + "var i=s.indexOf('-');"
                        + "tz=s.substring(i+1);"
                        + "timeOffset=parseInt(tz.substring(0,2))*3600000;"
                        + "timeOffset+=parseInt(tz.substring(2,4))*60000;"
                        + "s=s.substring(0,i);"
                        + "};"
                        + "if(s.indexOf('+')!=-1){"
                        + "var i=s.indexOf('+');"
                        + "tz=s.substring(i+1);"
                        + "timeOffset=parseInt(tz.substring(0,2))*3600000;"
                        + "timeOffset+=parseInt(tz.substring(2,4))*60000;"
                        + "timeOffset*=-1;"
                        + "s=s.substring(0,i);"
                        + "};"
                        //+ "console.log('HL7,tz',timeOffset,s);"
                        + "for(const k in f){"
                        + "if(s.length==0){break;}"
                        + "var m=Math.min(f[k].l,s.length);"
                        + "var v=s.substring(0,m);"
                        //+ "console.log('HL7,'+k+',value',v);"
                        + "f[k].fn(v,d);"
                        + "s=s.substring(m);"
                        //+ "console.log('HL7,'+k,d,s);"
                        + "};" // end for
                        + "d.tz=timeOffset;"
                        + "d.value=Date.UTC(d.year,d.month,d.date,d.hour,d.minute,d.second,d.millis);"
                        + "return d;"
                        + "};",
                JsParameter.getInstance("text", JsType.STRING))
        );

        add(createFunction(PARSE_FN, JsType.OBJECT,
                "(text)=>{"
                        + "const fn={"
                        + "1:{id:'Surname',type:'ST',length:0,parse:parseST,value:null},"
                        + "2:{id:'Own Surname Prefix',type:'ST',length:0,parse:parseST,value:null},"
                        + "3:{id:'Own Surname',type:'ST',length:0,parse:parseST,value:null},"
                        + "4:{id:'Surname Prefix From Partner/Spouse',type:'ST',length:0,parse:parseST,value:null},"
                        + "5:{id:'Surname From Partner/Spouse',type:'ST',length:0,parse:parseST,value:null}"
                        + "};"
                        + "if(text==undefined||text==null){"
                        + "return fn;"
                        + "};"
                        + "return parseData(fn,text.split('&'));"
                        + "};",
                JsParameter.getInstance("value", JsType.STRING))
        );

        add(createFunction(PARSE_CN, JsType.OBJECT,
                "(text)=>{"
                        + "var cn={"
                        + "1:{id:'ID Number',type:'ST',length:0,parse:parseST,value:null},"
                        + "2:{id:'Family Name',type:'ST',length:0,parse:parseST,value:null},"
                        + "3:{id:'Given Name',type:'ST',length:0,parse:parseST,value:null},"
                        + "4:{id:'Middle Initial Or Name',type:'ST',length:0,parse:parseST,value:null},"
                        + "5:{id:'Suffix',type:'ST',length:0,parse:parseST,value:null},"
                        + "6:{id:'Prefix',type:'ST',length:0,parse:parseST,value:null},"
                        + "7:{id:'Degree',type:'ST',length:0,parse:parseST,value:null},"
                        + "8:{id:'Source Table',type:'ID',length:0,parse:parseID,value:null},"
                        + "9:{id:'Assigning Authority',type:'ST',length:0,parse:parseST,value:null}"
                        + "};"
                        + "if(text==undefined||text==null){"
                        + "return cn;"
                        + "};"
                        + "return parseData(cn,text.split('^'));"
                        + "};",
                JsParameter.getInstance("text", JsType.STRING))
        );

        add(createFunction(PARSE_XCN, JsType.OBJECT,
                "(text)=>{"
                        + "var xcn={"
                        + "1:{id:'Person Identifier',type:'ST',length:0,parse:parseST,value:null},"
                        + "2:{id:'Family Name',type:'FN',length:0,parse:parseFN,value:null},"
                        + "3:{id:'Given Name',type:'ST',length:0,parse:parseST,value:null},"
                        + "4:{id:'Second And Further Given Names Or Initials Thereof',type:'ST',length:0,parse:parseST,value:null},"
                        + "5:{id:'Suffix',type:'ST',length:0,parse:parseST,value:null},"
                        + "6:{id:'Prefix',type:'ST',length:0,parse:parseST,value:null},"
                        + "7:{id:'Degree',type:'ST',length:0,parse:parseST,value:null}"
                        // other segments omitted
                        + "};"
                        + "if(text==undefined||text==null){"
                        + "return xcn;"
                        + "};"
                        + "return parseData(xcn,text.split('^'));"
                        + "};",
                JsParameter.getInstance("text", JsType.STRING))
        );
    }
}
