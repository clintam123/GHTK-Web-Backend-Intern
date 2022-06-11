package com.example.studentghtk.excel;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
public class ExcelController {
    @Autowired
    ExcelService excelService;

    @GetMapping("/import")
    public String showImport() {
        return "import";
    }

    @PostMapping("/import")
    public String handleImportFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        String message = "";
        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                excelService.save(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                redirectAttributes.addFlashAttribute("message", message);
                return "redirect:/import";
            } catch (Exception e) {
                System.out.println(e);
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                redirectAttributes.addFlashAttribute("message", message);
                return "redirect:/import";
            }
        }
        message = "Please upload an excel file!";
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/import";
    }
}