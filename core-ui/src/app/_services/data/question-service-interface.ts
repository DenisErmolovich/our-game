import {OldQuestion} from '../../_models/old-question';

/**
 * @deprecated for removal
 */
export interface QuestionServiceInterface {
  getQuestion(gameId: string, roundId: string, questionId: string): OldQuestion;
  updateQuestion(gameId: string, roundId: string, question: OldQuestion): void;
}
