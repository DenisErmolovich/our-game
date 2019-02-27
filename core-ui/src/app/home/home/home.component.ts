import { Component, OnInit } from '@angular/core';
import {GameService} from '../../_services/data/local-storage/game.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  constructor(
    private gameService: GameService,
    private router: Router
  ) { }

  ngOnInit() { }

  public newGame(): void {
    this.gameService.initGameFromJson();
  }

  public toGameList(): void {
    this.router.navigate(['game']);
  }

}
