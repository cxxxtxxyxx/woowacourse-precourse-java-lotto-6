package lotto.domain;

import lotto.common.util.NumberGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Lottos {

    private final List<Lotto> lottos;

    private Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos createByCount(int lottoCount, NumberGenerator<List<Integer>> lottoNumberGenerator) {

        List<Lotto> lottos = IntStream.range(0, lottoCount)
                .mapToObj(idx -> {
                    List<Integer> lottoNumbers = new ArrayList<>(lottoNumberGenerator.generate());
                    Collections.sort(lottoNumbers);
                    return new Lotto(lottoNumbers);
                })
                .toList();

        return new Lottos(lottos);
    }

    public int size() {
        return lottos.size();
    }

    public List<LottoRanking> calculateRankings(WinningLotto winningLotto) {
        return lottos.stream()
                .map(winningLotto::calculateRanking)
                .toList();
    }

    public List<Lotto> getLottos() {
        return List.copyOf(lottos);
    }
}
