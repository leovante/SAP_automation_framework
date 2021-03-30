package com.utils.sapElement;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.EnumVariant;
import com.jacob.com.Variant;

import java.util.LinkedList;
import java.util.List;

public class ByNameAndGuiClassCollection implements SapElementCollection {
    By criteria;
    GuiClassName[] guiClassName;
    ActiveXComponent session;
    Long typeAsNumber;

    public ByNameAndGuiClassCollection(ActiveXComponent session, By criteria, Long typeAsNumber, GuiClassName... guiClassName) {
        this.criteria = criteria;
        this.guiClassName = guiClassName;
        this.session = session;
        this.typeAsNumber = typeAsNumber;
    }

    @Override
    public List<ActiveXComponent> getSapElements() {
        List<ActiveXComponent> sapElementList = new LinkedList<>();
        if (guiClassName.length == 0 && typeAsNumber == null) {
            String name = new TypeAsNumberDictionary().getName(criteria.toString());
            Long num = new TypeAsNumberDictionary().getNum(criteria.toString());

            Variant[] arg = new Variant[2];
            arg[0] = new Variant(name);
            arg[1] = new Variant(num);
            ActiveXComponent ac = new ActiveXComponent(session.invoke("FindAllByNameEx", arg).toDispatch());
            EnumVariant en = new EnumVariant(ac);
            while (en.hasMoreElements()) {
                Dispatch dis = en.nextElement().getDispatch();
                ActiveXComponent ac2 = new ActiveXComponent(dis);
//                System.out.println(ac2.getProperty("ID").toString());
                sapElementList.add(ac2);
            }
            return sapElementList;
        } else if (guiClassName.length > 0) {
            Variant[] arg = new Variant[2];
            arg[0] = new Variant(criteria.toString());
            arg[1] = new Variant(guiClassName[0].toString());
        /*String sMoniker = "winmgmts:{impersonationLevel=impersonate}!" +
                "\\\\.\\root\\cimv2";
        Dispatch dServ = new Dispatch(sMoniker);

        Variant v = Dispatch.call(dServ, "SubclassesOf");
        // SWbemObjectSet object
        // http://msdn.microsoft.com/en-us/library/aa393762%28v=vs.85%29.aspx
        Dispatch objSet = v.getDispatch();
        System.out.println("object count: " + objSet.get(objSet, "count"));
        int cObj = 0;
        EnumVariant en = new EnumVariant(objSet);
        while (en.hasMoreElements()) {
            // SWbemObject object
            // http://msdn.microsoft.com/en-us/library/aa393741(v=vs.85).aspx
            Dispatch dItem = en.nextElement().getDispatch();
            Dispatch dPath = Dispatch.get(dItem, "Path_").getDispatch();
            String sClass = Dispatch.get(dPath, "Class").getString();
            System.out.println("path: " + sClass);
            Dispatch dObj = Dispatch.call(dServ, "get", sClass).getDispatch();
            Dispatch dProps = Dispatch.call(dObj, "Properties_").getDispatch();
            EnumVariant enProp = new EnumVariant(dProps);
            while (enProp.hasMoreElements()) {
                Dispatch dProp = enProp.nextElement().getDispatch();
                String sProp = Dispatch.get(dProp, "name").getString();
                System.out.println("property: " + sProp);
            }
            if (++cObj >= 5)
                break;
        }*/
        /*System.out.println("Начали");
        ActiveXComponent ac = new ActiveXComponent(session.invoke("FindAllByName", arg).toDispatch());
        int count = ac.get(ac, "count").toInt();
        System.out.println("Строк " + count);
        for (int i = 0; i < count; i++) {
//    /app/con[0]/ses[0]/wnd[0]/usr/tblSAPML04ID1021/ctxtLINP-LGPLA[1,0]
            Dispatch disp = ac.invoke("Children").toDispatch();
            System.out.println("Всего детей "+ disp.call(disp,"count").toInt());

            System.out.println("Зашел в цикл");
            ActiveXComponent ac2 = new ActiveXComponent(ac.invoke("Children", i).toDispatch());
            System.out.println("что тут " + ac2.getProperty("ID").toString());
        }*/
            ActiveXComponent ac = new ActiveXComponent(session.invoke("FindAllByName", arg).toDispatch());
            EnumVariant en = new EnumVariant(ac);
            while (en.hasMoreElements()) {
                Dispatch dis = en.nextElement().getDispatch();
                ActiveXComponent ac2 = new ActiveXComponent(dis);
//                System.out.println(ac2.getProperty("ID").toString());
                sapElementList.add(ac2);
            }
            return sapElementList;
        } else if (typeAsNumber != null) {
            Variant[] arg = new Variant[2];
            arg[0] = new Variant(criteria.toString());
            arg[1] = new Variant(typeAsNumber);
            ActiveXComponent ac = new ActiveXComponent(session.invoke("FindAllByNameEx", arg).toDispatch());
            EnumVariant en = new EnumVariant(ac);
            while (en.hasMoreElements()) {
                Dispatch dis = en.nextElement().getDispatch();
                ActiveXComponent ac2 = new ActiveXComponent(dis);
//                System.out.println(ac2.getProperty("ID").toString());
                sapElementList.add(ac2);
            }
            return sapElementList;
        }
        return null;
    }

    @Override
    public ActiveXComponent getSession() {
        return session;
    }
}
