import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {RoundService} from '../../../_services/data/local-storage/round.service';
import {OldSuperRound} from '../../../_models/old-super-round';

@Component({
  selector: 'app-super-round',
  templateUrl: './super-round.component.html',
  styleUrls: ['./super-round.component.scss']
})
export class SuperRoundComponent implements OnInit {
  public gameId: string;
  public superRound: OldSuperRound;
  public hideQuestionMap = new Map<string, boolean>();

  constructor(
    private activateRout: ActivatedRoute,
    private roundService: RoundService,
    private router: Router
  ) { }

  ngOnInit() {
    this.gameId = this.activateRout.snapshot.paramMap.get('gameId');
    this.superRound = this.roundService.getSuperRound(this.gameId);
    this.initHideQuestionMap();
  }

  public goToQuestion(gameId: string, questionId: string): void {
    let counter = 0;
    for (const question of this.superRound.questions) {
      if (!this.hideQuestionMap.get(question.id)) {
        counter++;
      }
      if (counter > 1) {
        this.hideQuestionMap.set(questionId, true);
        return;
      }
    }
    this.router.navigate(['game', gameId, 'round', 'super', 'question', questionId]);
  }

  private initHideQuestionMap(): void {
    for (const question of this.superRound.questions) {
      this.hideQuestionMap.set(question.id, false);
    }
  }

}
