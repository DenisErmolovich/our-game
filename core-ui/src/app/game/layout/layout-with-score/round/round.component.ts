import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {OldRound} from '../../../../_models/old-round';
import {RoundService} from '../../../../_services/data/local-storage/round.service';
import {QuestionService} from '../../../../_services/data/local-storage/question.service';

@Component({
  selector: 'app-round',
  templateUrl: './round.component.html',
  styleUrls: ['./round.component.scss']
})
export class RoundComponent implements OnInit {
  public gameId: string;
  public roundId: string;
  public round: OldRound;

  constructor(
    private activateRout: ActivatedRoute,
    private roundService: RoundService,
    private questionService: QuestionService,
    private router: Router
  ) { }

  ngOnInit() {
    const paramMap = this.activateRout.snapshot.paramMap;
    this.gameId = paramMap.get('gameId');
    this.roundId = paramMap.get('roundId');
    this.round = this.roundService.getRound(this.gameId, this.roundId);
  }

  public getQuestionValue(questionId: string): number {
    return this.questionService.countQuestionValue(this.gameId, this.roundId, questionId);
  }

  public goToQuestion(gameId: string, roundId: string, questionId: string): void {
    this.router.navigate(['game', gameId, 'round', roundId, 'question', questionId]);
  }

}
