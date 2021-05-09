package com.notedgeek.notes;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class NotesController {

    private final AsciidoctorConverter converter;

    private static final String DEFAULT_ASCIIDOC = "//EDIT TEXT AND/OR CLICK ON THE RENDER BUTTON TO RENDER " +
        "THIS SNIPPET\n" +
        "= HEADING 1\n" +
        "== Heading 2\n" +
        "=== heading 3\n" +
        "==== heading 4\n" +
        "This is just\n" +
        "some text.\n" +
        "\n" +
        "This is another paragraph,\n" +
        "with some `code`,  *bold*, and _italic_ text.\n" +
        "[source, java]\n" +
        "----\n" +
        "@RequestMapping(\"convert\")\n" +
        "public String convert(\n" +
        "    @RequestParam(name = \"asciidoc\", defaultValue = DEFAULT_ASCIIDOC) String asciidoc, \n" +
        "    Model model\n" +
        ") {\n" +
        "    String html = AsciidoctorConverter.convert(asciidoc);\n" +
        "    model.addAttribute(\"html\", html);\n" +
        "    return \"render\";\n" +
        "}\n" +
        "----"
        ;

    @GetMapping()
    public String convertFormGet(Model model) {
        model.addAttribute("defaultAdoc", DEFAULT_ASCIIDOC);
        return "convertForm";
    }

    @PostMapping("convert")
    public String convert(
        @RequestParam(name = "asciidoc", defaultValue = DEFAULT_ASCIIDOC) String asciidoc, Model model
    ) {
        model.addAttribute("html", converter.convert(asciidoc));
        return "render";
    }
}
