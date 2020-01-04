import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {QuestionService} from '../../../../_services/data/local-storage/question.service';
import {OldQuestion} from '../../../../_models/old-question';
import {QuestionState} from '../../../../_enums/question-state.enum';
import {QuestionTypes} from '../../../../_enums/question-types.enum';
import {PlayerService} from '../../../../_services/data/local-storage/player.service';
import {OldUser} from '../../../../_models/old-user';
import {FormControl, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-question',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.scss']
})
export class QuestionComponent implements OnInit, OnDestroy {
  public gameId: string;
  public roundId: string;
  public questionId: string;
  public question: OldQuestion;
  public state: QuestionState;
  public players: Array<OldUser>;
  public answeredUsersMap = new Map<string, boolean>();
  public formGroup = new FormGroup({});

  private audio = new Audio();

  constructor(
    private activateRout: ActivatedRoute,
    private questionService: QuestionService,
    private playerService: PlayerService,
    private router: Router
  ) { }

  ngOnInit() {
    this.initParams();
    this.question = this.questionService.getQuestion(this.gameId, this.roundId, this.questionId);
    this.players = this.playerService.getPlayersByGameId(this.gameId);
    this.fillMap();
    this.initFormArray();
    this.checkState();
  }

  public showTopic(): void {
    if (this.question.topic) {
      this.state = QuestionState.TOPIC;
    } else {
      this.showQuestion();
    }
  }

  public showQuestion(): void {
    this.state = QuestionState.QUESTION;
    if (this.question.type !== QuestionTypes.SUPER) {
      this.audio.src = '/assets/sounds/system/NoAnswer.mp3';
      setTimeout(() => {
        if (this.audio) {
          this.audio.play();
        }
      }, 60 * 1000);
    }
  }

  public showAnswer(): void {
    this.audio = null;
    this.question.isAnswered = true;
    this.questionService.updateQuestion(this.gameId, this.roundId, this.question);
    this.state = QuestionState.ANSWER;
  }

  public updateScore(userId: string, isRight: boolean): void {
    let price = this.questionService.countQuestionValue(this.gameId, this.roundId, this.questionId);
    const customPrice = +this.formGroup.controls[userId].value;
    if (customPrice) {
      price = customPrice;
    }
    if (!isRight) {
      price = price * -1;
    }
    this.answeredUsersMap.set(userId, true);
    this.playerService.updateScore(this.gameId, userId, price);
  }

  public goToQuestions(): void {
    this.router.navigate(['game', this.gameId, 'round', this.roundId]);
  }

  private initParams(): void {
    const paramMap = this.activateRout.snapshot.paramMap;
    this.gameId = paramMap.get('gameId');
    this.roundId = paramMap.get('roundId');
    this.questionId = paramMap.get('questionId');
  }

  private fillMap(): void {
    for (const player of this.players) {
      this.answeredUsersMap.set(player.id, false);
    }
  }

  private initFormArray(): void {
    for (const player of this.players) {
      this.formGroup.addControl(player.id, new FormControl());
    }
  }

  private checkState(): void {
    if (this.question.type === QuestionTypes.AUCTION || this.question.type === QuestionTypes.CAT) {
      this.state = QuestionState.TYPE;
    } else if (this.question.isAnswered) {
      this.showAnswer();
    } else {
      this.showQuestion();
    }
  }

  ngOnDestroy(): void {
    if (this.audio) {
      this.audio = null;
    }
  }

}
