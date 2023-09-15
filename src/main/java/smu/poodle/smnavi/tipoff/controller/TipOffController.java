package smu.poodle.smnavi.tipoff.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smu.poodle.smnavi.common.dto.BaseResponse;
import smu.poodle.smnavi.common.dto.PageResult;
import smu.poodle.smnavi.tipoff.dto.TipOffRequestDto;
import smu.poodle.smnavi.tipoff.dto.TipOffResponseDto;
import smu.poodle.smnavi.tipoff.service.TipOffService;

@RestController
@RequiredArgsConstructor
public class TipOffController {
    private final TipOffService tipOffService;

    /**
     * 제보글 작성
     */
    @PostMapping("/api/info")
    public BaseResponse<TipOffResponseDto.Simple> registerTipOff(@RequestBody @Valid TipOffRequestDto tipOffRequestDto) {

        return BaseResponse.ok(tipOffService.registerTipOff(tipOffRequestDto));
    }

    /**
     * 제보글 목록 조회
     */
    @GetMapping("/api/info") //제보 전체 조회
    public BaseResponse<PageResult<TipOffResponseDto.Detail>> getTipOffList(
            @RequestParam(required = false) String keyword,
            Pageable pageable) {
        return BaseResponse.ok(tipOffService.getTipOffList(keyword, pageable));
    }


    /**
     * 제보글 단건 조회
     */
    @GetMapping("/api/info/{id}") //제보 id별로 조회
    public BaseResponse<TipOffResponseDto.Detail> getTipOffById(@PathVariable(value = "id") Long id) {
        TipOffResponseDto.Detail infoDtoId = tipOffService.getTipOffById(id);
        return BaseResponse.ok(infoDtoId);
    }

    /**
     * 제보 글 버튼 조회 API
     */
    //todo : BaseResponse 로 리팩토링
    @GetMapping("/api/info/button")
    public ResponseEntity<?> getTipOffButton() {

        return ResponseEntity.ok().body(tipOffService.getTipOffButton());
    }

    /**
     * 제보 글 수정
     */
    @PutMapping("/api/info/{id}")
    public ResponseEntity<?> updateInfo(@PathVariable(value = "id") Long id, @RequestBody TipOffRequestDto tipOffRequestDto) {
        TipOffResponseDto.Detail updateInfo = tipOffService.updateInfo(id, tipOffRequestDto);
        if (updateInfo != null) {
            return ResponseEntity.ok().body(tipOffRequestDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 제보글 삭제
     */
    @DeleteMapping("/api/info/{id}")
    public BaseResponse<Void> deleteTipOff(@PathVariable(value = "id") Long id) {
        tipOffService.deleteTipOff(id);
        return BaseResponse.ok();
    }
}
