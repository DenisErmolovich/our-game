import { Injectable } from '@angular/core';
import {QuestionServiceInterface} from '../question-service-interface';
import {OldQuestion} from '../../../_models/old-question';
import {GameService} from './game.service';

@Injectable({
  providedIn: 'root'
})
export class QuestionService implements QuestionServiceInterface {

  constructor(
    private gameService: GameService
  ) { }

  public getQuestion(gameId: string, roundId: string, questionId: string): OldQuestion {
    const game = this.gameService.getById(gameId);
    if (roundId === 'super') {
      return game.superRound.questions.find(question => question.id === questionId);
    } else {
      const round = game.rounds.find(item => item.id === roundId);
      for (const topic of round.topics) {
        const question = topic.questions.find(item => item.id === questionId);
        if (question) {
          return question;
        }
      }
      return undefined;
    }
  }

  public countQuestionValue(gameId: string, roundId: string, questionId: string): number {
    const game = this.gameService.getById(gameId);
    for (let roundNumber = 0; roundNumber < game.rounds.length; roundNumber++) {
      const round = game.rounds[roundNumber];
      if (round.id === roundId) {
        for (let topicNumber = 0; topicNumber < round.topics.length; topicNumber++) {
          const topic = round.topics[topicNumber];
          for (let questionNumber = 0; questionNumber < topic.questions.length; questionNumber++) {
            const question = topic.questions[questionNumber];
            if (question.id === questionId) {
              return (game.settings.minPrice * (questionNumber + 1)) * (roundNumber + 1);
            }
          }
        }
      }
    }
    return null;
  }

  public updateQuestion(gameId: string, roundId: string, question: OldQuestion): void {
    const games = this.gameService.getAll();
    const game = games.find(item => item.id === gameId);
    if (roundId === 'super') {
      const superRound = game.superRound;
      const questionIndex = superRound.questions.findIndex(item => item.id === question.id);
      superRound.questions[questionIndex] = question;
    } else {
      const round = game.rounds.find(item => item.id === roundId);
      let topicIndex: number;
      for (let i = 0; i < round.topics.length; i++) {
        if (round.topics[i].questions.find(item => item.id === question.id)) {
          topicIndex = i;
        }
      }
      const questionIndex = round.topics[topicIndex].questions.findIndex(item => item.id === question.id);
      round.topics[topicIndex].questions[questionIndex] = question;
    }
    localStorage.setItem('games', JSON.stringify(games));
  }
}
