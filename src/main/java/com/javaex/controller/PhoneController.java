package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.PhoneService;
import com.javaex.vo.PersonVo;

@Controller
//@RequestMapping(value="/guest") -->공통주소
public class PhoneController {

	// 필드
	@Autowired
	private PhoneService phoneService;

	// 생성자

	// 메소드-gs

	// 메소드-일반

	// 전화번호 리스트
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model) {
		System.out.println("PhoneController>list()");

		// Service를 통해서 personList(주소)을 가져온다
		// PhoneService phoneService = new PhoneService();
		List<PersonVo> personList = phoneService.getPersonList();

		// ds 데이터보내기 --> request attribute에 넣는다
		model.addAttribute("personList", personList);

		return "list";
	}

	// 전화번호 등록 폼
	@RequestMapping(value = "/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm() {
		System.out.println("PhoneController>writeForm()");
		return "writeForm";
	}

	// 전화번호 등록
	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute PersonVo personVo) {
		System.out.println("PhoneController>write()");

		// 파라미터 꺼내기 + vo로 묶기를 DS해서 메소드의 파라미터로 보내준다

		// Service를 통해서 저장한다

		int count = phoneService.personInsert(personVo);

		// 리다이렉트
		return "redirect:/list";
	}

	// 전화번호 삭제

	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@RequestParam("no") int no) {
		System.out.println("PhoneController>delete()");

		// 파라미터 꺼내기
		System.out.println(no);

		// Service를 통해서 삭제한다
		int count = phoneService.personDelete(no);
		System.out.println(count);

		return "redirect:/list";
	}

	// 전화번호 수정폼
	@RequestMapping(value = "/updateForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String upeateForm(Model model, @RequestParam("no") int no) {
		System.out.println("PhoneController>updateForm()");

		// Service를 통해서 1명정보 가져오기
		PersonVo personVo = phoneService.getPerson(no);

		model.addAttribute("personVo", personVo);
		return "updateForm";

	}

	// 전화번호 수정
	@RequestMapping(value = "/update", method = { RequestMethod.GET, RequestMethod.POST })
	public String update(@ModelAttribute PersonVo personVo) {
		System.out.println("PhoneController>update");

		System.out.println(personVo);

		// Service를 통해서 수정하기
		int count = phoneService.personUpdate(personVo);
		System.out.println(count);

		return "redirect:list";

	}

}
