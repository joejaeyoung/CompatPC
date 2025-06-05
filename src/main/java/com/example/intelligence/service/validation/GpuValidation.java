package com.example.intelligence.service.validation;

import com.example.intelligence.domain.hardware.CPU;
import com.example.intelligence.domain.hardware.GPU;
import com.example.intelligence.domain.hardware.Cases;
import com.example.intelligence.domain.hardware.PSU;
import com.example.intelligence.service.validation.dto.validation.ServiceUserRequest;
import com.example.intelligence.service.validation.dto.validation.ServiceValidationResponse;

import java.util.ArrayList;
import java.util.List;

public class GpuValidation {
    public List<ServiceValidationResponse> errorMsg = new ArrayList<>();

    public void checkWithCpu(ServiceUserRequest request,  CPU cpu) {
        //500
        if(request.getGpuId() == null && !cpu.isHasGPU()) {
            errorMsg.add(new ServiceValidationResponse(
                    "CPU에 내장그래픽이 포함되어 있지 않은 제품입니다.",
                    "내장그래픽이 포함된 CPU를 선택해주세요.\n또는 그래픽카드를 선택해주세요.",
                    1));
        }
    }

    public void checkWithCase(ServiceUserRequest request, GPU gpu, Cases cases) {
        boolean isGpuInside = request.getGpuId() == null;
        if(isGpuInside) return;
        //850
        if(!cases.isSupportsVerticalPCI() && cases.isLPcase()) {
            if(!gpu.isHasLPbracket()) {
                errorMsg.add(new ServiceValidationResponse("LP 케이스에 일반 그래픽카드를 장착할 수 없습니다.", "LP 브래킷이 포함된 그래픽카드로 교체하거나 표준 크기 케이스로 변경해 주세요.", 1));
            }
        }

        if(gpu.getLength() < 0) {
            errorMsg.add(new ServiceValidationResponse("그래픽카드의 길이 정보가 없는 제품입니다.", "선택하신 그래픽카드의 길이 정보가 등록되어 있지 않아, 케이스와의 물리적 호환 여부를 확인할 수 없습니다.\n" +
                    "길이 정보가 확인된 그래픽카드로 변경해 주세요.\n" + "또는 케이스와의 호환 여부를 제조사에 문의하세요.", 0));
            return;
        }
        //851
        if(gpu.getLength() > cases.getMaxGpuLength()) {
            errorMsg.add(new ServiceValidationResponse("그래픽카드 길이가 케이스가 허용하는 한계를 초과합니다.", "선택하신 그래픽카드의 길이 [GPU 길이]mm는 케이스가 허용하는 최대 VGA 길이 [케이스 허용 길이]mm를 초과합니다.\n" +
                    "이로 인해 그래픽카드를 케이스에 장착할 수 없습니다.\n" + "더 짧은 길이의 그래픽카드를 선택하거나 더 긴 길이의 그래픽카드를 지원하는 케이스로 변경해 주세요.", 1));;
        }
    }

    public void checkWithPsu(ServiceUserRequest request, CPU cpu, GPU gpu, PSU psu) {
        //950
        boolean isGpuInside = request.getGpuId() == null;
        if(isGpuInside) {
            double modifiedOutput = psu.getOutput() / 1.2 * (cpu.getTdp() + 100);
            if(modifiedOutput < 1.0) {
                errorMsg.add(new ServiceValidationResponse(" ", "현재 구성의 예상 소비 전력 대비 파워서플라이의 용량이 부족합니다.\n" +
                        "시스템이 부팅되지 않거나 사용 중 전력 부족으로 인한 문제가 발생할 수 있습니다.\n" + "더 높은 정격 출력을 가진 파워서플라이로 교체해 주세요.\n" +
                        "또는 구성 부품의 소비 전력을 낮춰 주세요.", 1));
                return;
            }
            if(modifiedOutput >= 1.0 && modifiedOutput <= 1.2) {
                errorMsg.add(new ServiceValidationResponse(" ", "파워 용량이 시스템 예상 소비 전력보다 약간 높지만, 여유가 크지 않습니다.\n" + "" +
                        "장시간 고부하 사용 시 불안정할 수 있으며, 파워 팬 소음이나 발열 증가가 발생할 수 있습니다.\n" + "더 높은 정격 출력을 가진 파워서플라이로 교체하는걸 권장합니다.\n" +
                        "특히 GPU, CPU 오버클럭 또는 고사양 작업 시 반드시 교체가 필요합니다.\n" + "또는 구성 부품의 소비 전력을 낮춰 주세요.", 0));
                return;
            }
        }
        else {
            if(gpu.getRecommendedPsuOutput() >= 0) {
                double modifiedOutput = psu.getOutput() / (gpu.getRecommendedPsuOutput() - 350 + cpu.getTdp() * 1.2);
                if(modifiedOutput < 1.0) {
                    errorMsg.add(new ServiceValidationResponse(" ", "현재 구성의 예상 소비 전력 대비 파워서플라이의 용량이 부족합니다.\n" +
                            "시스템이 부팅되지 않거나 사용 중 전력 부족으로 인한 문제가 발생할 수 있습니다.\n" + "더 높은 정격 출력을 가진 파워서플라이로 교체해 주세요.\n" +
                            "또는 구성 부품의 소비 전력을 낮춰 주세요.", 1));
                    return;
                }
                if(modifiedOutput >= 1.0 && modifiedOutput <= 1.2) {
                    errorMsg.add(new ServiceValidationResponse(" ", "파워 용량이 시스템 예상 소비 전력보다 약간 높지만, 여유가 크지 않습니다.\n" + "" +
                            "장시간 고부하 사용 시 불안정할 수 있으며, 파워 팬 소음이나 발열 증가가 발생할 수 있습니다.\n" + "더 높은 정격 출력을 가진 파워서플라이로 교체하는걸 권장합니다.\n" +
                            "특히 GPU, CPU 오버클럭 또는 고사양 작업 시 반드시 교체가 필요합니다.\n" + "또는 구성 부품의 소비 전력을 낮춰 주세요.", 0));
                    return;
                }
            }
            else if(gpu.getTdp() >= 0) {
                double modifiedOutput = (double)psu.getOutput() / gpu.getTdp() + 1.2 * (cpu.getTdp() + 100);
                if(modifiedOutput < 1.0) {
                    errorMsg.add(new ServiceValidationResponse(" ", "현재 구성의 예상 소비 전력 대비 파워서플라이의 용량이 부족합니다.\n" +
                            "시스템이 부팅되지 않거나 사용 중 전력 부족으로 인한 문제가 발생할 수 있습니다.\n" + "더 높은 정격 출력을 가진 파워서플라이로 교체해 주세요.\n" +
                            "또는 구성 부품의 소비 전력을 낮춰 주세요.", 1));
                    return;
                }
                if(modifiedOutput >= 1.0 && modifiedOutput <= 1.2) {
                    errorMsg.add(new ServiceValidationResponse(" ", "파워 용량이 시스템 예상 소비 전력보다 약간 높지만, 여유가 크지 않습니다.\n" + "" +
                            "장시간 고부하 사용 시 불안정할 수 있으며, 파워 팬 소음이나 발열 증가가 발생할 수 있습니다.\n" + "더 높은 정격 출력을 가진 파워서플라이로 교체하는걸 권장합니다.\n" +
                            "특히 GPU, CPU 오버클럭 또는 고사양 작업 시 반드시 교체가 필요합니다.\n" + "또는 구성 부품의 소비 전력을 낮춰 주세요.", 0));
                    return;
                }
            }
            else {
                errorMsg.add(new ServiceValidationResponse("그래픽카드의 전력 정보가 없어 파워 용량 검증이 불가능합니다.", "선택한 그래픽카드에 대한 권장 파워 용량 및 TDP 정보가 모두 누락되어 있어,\n" +
                        "시스템의 전력 소모량을 정확히 계산할 수 없습니다. 이로 인해 파워서플라이 용량이 적절한지 판단할 수 없습니다.\n" +
                        "GPU의 권장 파워 및 TDP 정보를 확인 가능한 제품으로 변경해 주세요.", 1));
            }
        }

        if(isGpuInside) return;
        if(gpu.getRequired16PinCount() < 0 && gpu.getRequired8PinCount() < 0 && gpu.getRequired6PinCount() < 0) {
            errorMsg.add(new ServiceValidationResponse("그래픽카드의 전원 커넥터 정보가 확인되지 않습니다.", "선택하신 그래픽카드의 PCIe 보조 전원 핀(6핀/8핀/16핀) 정보가 없어, 파워 서플라이와의 호환 여부를 확인할 수 없습니다.\n" +
                    "파워 서플라이와의 호환 정보를 그래픽카드 제조사에 문의하세요.\n" + "또는 전원 커넥터 정보가 명확한 다른 그래픽카드를 선택해 주세요.\n", 0));
            return;
        }
        //951a
        if(gpu.getRequired16PinCount() != -1) {
            if (gpu.getRequired16PinCount() > psu.getPcie16PinCount()) {
                errorMsg.add(new ServiceValidationResponse("파워서플라이가 그래픽카드의 16핀 전원 요구사항을 만족하지 않습니다.", "그래픽카드가 16핀 보조전원을 요구하지만, 파워서플라이가 이를 제공하지 않습니다.\n" +
                        "이로 인해 그래픽카드에 전원을 공급할 수 없습니다.\n" + "16핀 전원 커넥터 수가 충분한 파워서플라이로 변경해 주세요.", 1));
                return;
            }
        }
        int modifiedPsuPcie6Count = psu.getPcie6PinCount();
        //951b
        if(gpu.getRequired8PinCount() != -1) {
            if(gpu.getRequired8PinCount() > psu.getPcie8PinCount()) {
                errorMsg.add(new ServiceValidationResponse("파워서플라이가 그래픽카드의 8핀 전원 요구사항을 만족하지 않습니다.", "파워서플라이의 8핀 출력이 그래픽카드가 요구하는 수량보다 부족합니다.\n" +
                        "이로 인해 그래픽카드에 전원을 공급할 수 없습니다.\n" + "8핀 전원 커넥터 수가 충분한 파워서플라이로 변경해 주세요.", 1));
                return;
            }
            else {
                modifiedPsuPcie6Count -= gpu.getRequired8PinCount();
            }
        }
        //951c
        if(gpu.getRequired6PinCount() != -1) {
            if(gpu.getRequired6PinCount() > modifiedPsuPcie6Count) {   //fix : 0601
                errorMsg.add(new ServiceValidationResponse("파워서플라이가 그래픽카드의 6핀 전원 요구사항을 만족하지 않습니다.", "파워서플라이의 6핀 출력이 그래픽카드가 요구하는 수량보다 부족합니다.\n" +
                        "이로 인해 그래픽카드에 전원을 공급할 수 없습니다.\n" + "6핀 전원 커넥터 수가 충분한 파워서플라이로 변경해 주세요.", 1));
                return;
            }
        }
    }
}
