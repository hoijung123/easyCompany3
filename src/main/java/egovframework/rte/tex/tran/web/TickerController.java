/*
 * Copyright 2011 MOPAS(Ministry of Public Administration and Security).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egovframework.rte.tex.tran.web;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.tex.com.service.EgovUserUtil;
import egovframework.rte.tex.com.service.SearchVO;
import egovframework.rte.tex.mbr.service.MemberVO;
import egovframework.rte.tex.pcs.service.PurchaseVO;
import egovframework.rte.tex.tran.service.TickerDtlVO;
import egovframework.rte.tex.tran.service.TickerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.xml.MarshallingView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 구매 및 납품상태 정보를 관리하는 컨트롤러 클래스를 정의한다.
 * @author 실행환경 개발팀 이영진
 * @since 2011.06.07
 * @version 1.0
 * @see <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2011.06.07  이영진          최초 생성
 *
 * </pre>
 */
@Controller
public class TickerController {

	/**TickerService */
	@Resource(name = "tickerService")
	private TickerService tickerService;

	/**EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/**
	 * 전체 입찰내역 조회한다.(관리자용)
	 * @param searchVO 검색조건
	 * @param model
	 * @return "dlv/EgovDeliveryAllList"
	 * @throws Exception
	 */
	@RequestMapping("/tran/getTickerList.do")
	public String getTickerList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpServletRequest request) throws Exception {

		/** EgovPropertyService.sample */
		searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
		searchVO.setPageSize(propertiesService.getInt("pageSize"));

		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		TickerDtlVO purchaseVO = new TickerDtlVO();
		purchaseVO.setSearchVO(searchVO);

		List<TickerDtlVO> purchsList = tickerService.getTickerList(purchaseVO);
		model.addAttribute("resultList", purchsList);

		int totCnt = tickerService.getTickerListTotCnt(purchaseVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		List<?> dlvyInfo = tickerService.selectDeliveryInfoList();
		model.addAttribute("dlvyList", dlvyInfo);

		if (request.getParameter("status") != null) {
			model.addAttribute("statusChange", 1);
		} else {
			model.addAttribute("statusChange", 0);
		}

		MemberVO loginVO = EgovUserUtil.getMemberInfo();
		model.addAttribute("loginVO", loginVO);

		return "tran/getTickerList";
	}

	/**
	 * 납품상태를 변경한다.
	 * @param dlvySe 납품코드
	 * @param purchsId 입찰ID
	 * @return "redirect:/dlv/selectAllListPurchase.do?status=1"
	 * @throws Exception
	 */
	@RequestMapping("/tran/updateDlvySttus.do")
	public String updateDeliveryStatus(@ModelAttribute("searchVO") SearchVO searchVO, @RequestParam("dlvySe") String dlvySe, @RequestParam("purchaseId") String purchsId)
			throws Exception {
		PurchaseVO purchaseVO = new PurchaseVO();
		purchaseVO.setPurchsId(purchsId);
		purchaseVO.setDlvySe(dlvySe);

		tickerService.updateDeliveryStatus(purchaseVO);

		return "redirect:/dlv/selectAllListPurchase.do?status=1";
	}

	/**
	 * 전체 입찰내역을 xml 형태로 출력한다.
	 * @param searchVO 검색 조건
	 * @return xml페이지
	 * @throws Exception
	 */
	@Resource
	MarshallingView purMarshallingView;

	@RequestMapping("/tran/viewXML.do")
	public ModelAndView viewXML(@ModelAttribute("searchVO") SearchVO searchVO) throws Exception {

		List<PurchaseVO> purchsList = tickerService.selectAllPurchaseXml();

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("purchsList", purchsList);

		return new ModelAndView(purMarshallingView, model);
	}

}
