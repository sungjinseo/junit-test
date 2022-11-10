package dev.greatseo.template.api.member.controller;

import dev.greatseo.template.api.member.dto.MemberResponseDto;
import dev.greatseo.template.api.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/api/v1/member"}, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	
	/**
	 * @method 설명 : ID에 해당되는 회원의 상세정보를 요청한다.
	 * @param id
	 * @throws Exception
	 */
	@GetMapping(value = {"/{id}"})
	public ResponseEntity<MemberResponseDto> getMember(@PathVariable Long id) {
		
		return ResponseEntity.ok()
							 .body(memberService.getMemberById(id));
	}
	
}
