package com.salesmanager.admin.controller.store;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MerchantStoreController {
	
	@Value("${address.autocomplete.apikey}")
	private String addressAutocompleteApiKey;
	
	
	@RequestMapping("/admin/store")
	@Secured({"ROLE_STORE"})
	public String display(@RequestParam(value = "code", required=false) String code, Principal principal, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		request.setAttribute("action", "READ");
		String cd = (String)request.getAttribute("code");
		if(!StringUtils.isBlank(code)) {
		  cd = code;
		}
		request.setAttribute("code", cd);
		request.setAttribute("addressApiKey", addressAutocompleteApiKey);
		return "store/store";
	}
	
	@RequestMapping("/admin/store/create")
	@Secured({"ROLE_STORE"})
	public String create(Principal principal, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		request.setAttribute("action", "CREATE");
		request.setAttribute("addressApiKey", addressAutocompleteApiKey);
		return "store/store";
	}
	
	@RequestMapping("/admin/store/list")
	@Secured({"ROLE_STORE"})
	public String list(Principal principal, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return "store/list";
	}
	
    @RequestMapping("/admin/store/marketing")
    @Secured({"ROLE_STORE"})
    public String marketing(@RequestParam(value = "code", required=false) String code, Principal principal, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        request.setAttribute("action", "READ");
        request.setAttribute("code", code);
        return "store/marketing";
    }
    
    @RequestMapping("/admin/store/landing")
    @Secured({"ROLE_STORE"})
    public String landing(@RequestParam(value = "code", required=false) String code, Principal principal, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        request.setAttribute("action", "READ");
        request.setAttribute("merchant", code);
        return "store/landing";
    }

}
