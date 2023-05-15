package com.philips.dmis.swt.ui.toolkit.js;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.utils.ProtectedHashMap;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public interface JsModule {
    void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException;

    String getInitFunctionId();

    static Map<Class<? extends JsMember>, Pair<String, JsMember>> createIndex(Collection<JsMember> members) {
        Map<Class<? extends JsMember>, Pair<String, JsMember>> index = new ProtectedHashMap<>();
        for (JsMember jsMember : members) {
            index.put(jsMember.getClass(), new Pair<>(UNG.generate(jsMember.getClass()), jsMember));
        }
        return index;
    }

    static void updateIndex(Collection<Pair<String, JsMember>> members,
                            Map<Class<? extends JsMember>, Pair<String, JsMember>> index) {
        index.clear();
        for (Pair<String, JsMember> pair : members) {
            index.put(pair.getValue().getClass(), new Pair<>(UNG.generate(pair.getValue().getClass()), pair.getValue()));
        }
    }

    static JsMember getMemberInstance(Class<? extends JsMember> memberClass,
                                      Map<Class<? extends JsMember>, Pair<String, JsMember>> index) {
        Pair<String, JsMember> pair = index.get(memberClass);
        if (pair == null) {
            throw new IllegalArgumentException("member instance not found: " + memberClass.getName()
                    + " in: " + String.join(", ", index.keySet().stream().map(cls -> cls.getSimpleName()).toList())
            );
        }
        return pair.getValue();
    }

    static String getId(Class<? extends JsMember> memberClass,
                        Map<Class<? extends JsMember>, Pair<String, JsMember>> index) throws JsRenderException {
        Pair<String, JsMember> pair = index.get(memberClass);
        if (pair == null) {
            throw new JsRenderException("member id not found: " + memberClass.getName()
                    + " in: " + String.join(", ", index.keySet().stream().map(cls -> cls.getSimpleName()).toList())
            );
        }
        return pair.getKey();
    }

    static String getQualifiedId(String moduleId, Class<? extends JsMember> memberClass,
                                 Map<Class<? extends JsMember>, Pair<String, JsMember>> index) {
        return moduleId + "." + getId(memberClass, index);
    }


    static void renderModule(Toolkit toolkit, JsWriter js, String id,
                             Collection<Pair<String, JsMember>> members) {
        // NOTE: should be var to prevent re-assigning
        js.append("var %s=(function(){", id);

        List<Pair<String, JsMember>> sorted = members.stream().sorted(
                        Comparator.comparingInt(m0 -> getOrder(m0.getValue())))
                .toList();

        for (Pair<String, JsMember> pair : sorted) {
            JsMember jsMember = pair.getValue();
            if (jsMember instanceof JsComposite) {
                js.comment("begin composite: " + ((JsComposite) jsMember).getName());
                jsMember.renderJs(toolkit, js);
                js.comment("end composite: " + ((JsComposite) jsMember).getName());
            } else if (jsMember instanceof JsVariable) {
                js.append("var %s=", pair.getKey());
                jsMember.renderJs(toolkit, js);
                js.append(";");
            } else {
                js.append("const %s=", pair.getKey());
                jsMember.renderJs(toolkit, js);
                js.append(";");
            }
        }
        // export members
        js.comment("export");
        js.append("return {");
        int i = 0;
        for (Pair<String, JsMember> pair : members) {
            if (!pair.getValue().isPublic()) {
                continue;
            }
            if (i > 0) {
                js.append(",");
            }
            if (pair.getValue() instanceof JsSymbol) {
                js.append("%s:", pair.getKey());
                pair.getValue().renderJs(toolkit, js);
            } else {
                js.append("%s:%s",
                        pair.getValue().getPublicName(pair.getKey()),
                        pair.getKey());
            }
            i++;
        }
        js.append("}");
        js.append("})();");
    }

    static int getOrder(JsMember member) {
        if (member instanceof JsConstant) {
            return 0;
        } else if (member instanceof JsVariable) {
            return 1;
        } else if (member instanceof JsFunction) {
            return 2;
        } else if (member instanceof JsComposite) {
            return 3;
        }
        return 4;
    }
}
