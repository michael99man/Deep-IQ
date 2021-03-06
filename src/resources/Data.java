package resources;

import main.Engine;
import main.GUI;
import console.Token;

public final class Data {

	public static Function[][] levelList;
	public static final int[] ADVANCEMENT_MAX_VALUES = { 9, 8, 7, 6, 5, 0 };
	public static final int[] DO_NOTHING_ROLLS = { 7, 4, 3, 3, 3, 3 };

	// Newly created Token; Made to stop annoying warnings, as Token is added to
	// Engine in constructor.
	@SuppressWarnings("unused")
	private static Token t;

	public static final Function[][] functionList =
	// Stage 1
	// Rolls 8-10]
	{ { (new Function() {
		public void Action() {
			GUI.append("Sacrifice your best creature.");
		}
	}), (new Function() {
		public void Action() {
			t = new Token(1, 1, Engine.requestNumber() - 4);
			GUI.append("Put a 1/1 token on the battlefield (-4).");
		}
	}), (new Function() {
		public void Action() {
			t = new Token(1, 1, Engine.requestNumber() - 4);
			GUI.append("Put a 1/1 token on the battlefield (-4).");
		}
	}), },

	{
			// Stage 2
			// Rolls 5-10
			(new Function() {
				public void Action() {
					GUI.append("Put a 2/2 token on the battlefield (+0).");
					t = new Token(2, 2, Engine.requestNumber());
				}
			}), (new Function() {
				public void Action() {
					GUI.append("Put a 2/2 token on the battlefield (+0).");
					t = new Token(2, 2, Engine.requestNumber());
				}
			}), (new Function() {
				public void Action() {
					GUI.append("Put a 2/2 token on the battlefield (+0).");
					t = new Token(2, 2, Engine.requestNumber());
				}
			}), (new Function() {
				public void Action() {
					Engine.diq.stage = 4;
					GUI.append("Move Deep IQ up to Table IV.");
				}
			}), (new Function() {
				public void Action() {
					GUI.append("Exile your best creature.");
				}
			}), (new Function() {
				public void Action() {
					GUI.append("Exile your best creature.");
				}
			}), },

	{
			// Stage 3
			// Rolls 4-10
			(new Function() {
				public void Action() {
					GUI.append("Put a 2/2 token on the battlefield (+2).");
					t = new Token(2, 2, Engine.requestNumber() + 2);
				}
			}), (new Function() {
				public void Action() {
					GUI.append("Put a 2/1 token on the battlefield (+4).");
					t = new Token(2, 1, Engine.requestNumber() + 4);
				}
			}), (new Function() {
				public void Action() {
					GUI.append("Destroy your best land.");
				}
			}), (new Function() {
				public void Action() {
					Engine.diq.stage = 5;
					Engine.player.life -= 3;
					GUI.append("￼￼Move Deep IQ up to Table V and you lose three life.");
				}
			}), (new Function() {
				public void Action() {
					GUI.append("Put a 1/1 token on the battlefield (+1) and Deep IQ gets a free roll on Table II.");
					t = new Token(1, 1, Engine.requestNumber() + 1);
					Engine.stageRoll(2);	
				}
			}), (new Function() {
				public void Action() {
					GUI.append("Sacrifice your best creature.");
				}
			}), (new Function() {
				public void Action() {
					GUI.append("Roll on the Spooky Chart (-2).");
					Engine.spookyRoll(-2);
				}
			}), },

	{
			// Stage 4
			// Rolls 4-10
			(new Function() {
				public void Action() {
					GUI.append("￼￼Put a 4/4 token on the battlefield (+3).");
					t = new Token(4, 4, Engine.requestNumber() + 3);
				}
			}), (new Function() {
				public void Action() {
					GUI.append("Sacrifice your best creature.");
				}
			}), (new Function() {
				public void Action() {
					GUI.append("Destroy your best artifact or enchantment.");
				}
			}), (new Function() {
				public void Action() {
					GUI.append("Exile your best creature.");
				}
			}), (new Function() {
				public void Action() {
					Engine.player.life -= 5;
					GUI.append("You take 5 damage.");
				}
			}), (new Function() {
				public void Action() {
					GUI.append("￼￼Put a 2/4 token on the battlefield (+7).");
					t = new Token(2, 4, Engine.requestNumber() + 7);
				}
			}), (new Function() {
				public void Action() {
					GUI.append("Roll on the Spooky Chart (+0).");
					Engine.spookyRoll(0);
				}
			}), },

	{
			// Stage 5
			// Rolls 4-10
			(new Function() {
				public void Action() {
					GUI.append("￼￼Put a 3/4 token on the battlefield (+4).");
					t = new Token(3, 4, Engine.requestNumber() + 4);
				}
			}), (new Function() {
				public void Action() {
					GUI.append("Put a 2/2 token on the battlefield (+2) and Deep IQ gets a free roll on Table III.");
					Engine.stageRoll(3);
					t = new Token(2, 2, Engine.requestNumber() + 2);
				}
			}), (new Function() {
				public void Action() {
					GUI.append("Destroy your best artifact or enchantment.");
				}
			}), (new Function() {
				public void Action() {
					GUI.append("Put a 4/4 token on the battlefield (+1).");
					t = new Token(4, 4, Engine.requestNumber() + 1);
				}
			}), (new Function() {
				public void Action() {
					Engine.diq.stage = 1;
					GUI.append("Destroy all lands. Move Deep IQ to Stage 1.");
				}
			}), (new Function() {
				public void Action() {
					GUI.append("Exile two of your best creatures.");
				}
			}), (new Function() {
				public void Action() {
					GUI.append("Roll on the Spooky Chart (+2).");
					Engine.spookyRoll(2);
				}
			}), },

	{
			// Stage 6
			// Rolls 4-10
			(new Function() {
				public void Action() {
					GUI.append("Destroy all artifacts you control.");
				}
			}), (new Function() {
				public void Action() {
					GUI.append("￼￼Put a 4/5 token on the battlefield (+6).");
					t = new Token(4, 5, Engine.requestNumber() + 6);
				}
			}), (new Function() {
				public void Action() {
					GUI.append("Destroy your best creature.");
				}
			}), (new Function() {
				public void Action() {
					Engine.player.life -= 7;
					GUI.append("You take 7 damage.");
				}
			}), (new Function() {
				public void Action() {
					GUI.append("Destroy your best permanent.");
				}
			}), (new Function() {
				public void Action() {
					GUI.append("￼￼Put a 6/6 token on the battlefield (+6).");
					t = new Token(6, 6, Engine.requestNumber() + 6);
				}
			}), (new Function() {
				public void Action() {
					GUI.append("Roll on the Spooky Chart (+4).");
					Engine.spookyRoll(4);
				}
			}), }

	};

	public static void spooky(int mod) {
		int roll = Engine.requestNumber() + mod;

		if (roll == 8 || roll == 11) {
			GUI.tabAppend("Deep IQ has rolled an " + roll
					+ " on the Spooky Chart");
		} else {
			GUI.tabAppend("Deep IQ has rolled a " + roll
					+ " on the Spooky Chart");
		}

		if (roll <= 1) {
			t = new Token(Token.TYPE.Enchantment,
					"All creatures tokens Deep IQ control get +1/+1.");
			for (Token tok : Token.tokenList) {
				tok.power += 1;
				tok.toughness += 1;
			}
			GUI.tabAppend("Deep IQ plays an enchantment token. While this is on the battlefield, all of its creature tokens gain +1/+1.");
		} else if (roll == 2) {

			t = new Token(Token.TYPE.Artifact,
					"Reroll the first \"Do nothing\" result of every turn.");

			GUI.tabAppend("Deep IQ plays an artifact token. While this is on the battlefield, reroll the first \"Do nothing\" result of every turn.");
		} else if (roll == 3) {
			t = new Token(Token.TYPE.Artifact,
					"Deep IQ gets +1 to all die rolls.");
			Engine.boost = true;
			GUI.tabAppend("Deep IQ plays an enchantment token. While this is on the battlefield, Deep IQ gets +1 to all die rolls.");
		} else if (roll == 4) {
			GUI.tabAppend("Destroy all of your creatures.");
		} else if (roll == 5) {
			Engine.diq.life+= 5;
			Engine.diq.stage = 6;
			GUI.tabAppend("Deep IQ gains 5 life and moves up to Table VI if it isn't already there.");
		} else if (roll == 6) {
			Engine.player.life -= 10;
			GUI.tabAppend("You take 10 damage.");
		} else if (roll == 7) {
			Engine.diq.life += 10;
			GUI.tabAppend("Deep IQ gains 10 life.");
		} else if (roll == 8) {
			GUI.tabAppend("Sacrifice a permanent of every type.");
		} else if (roll == 9) {
			GUI.tabAppend("Exile the top twenty cards in your library.");
		} else if (roll == 10) {
			GUI.tabAppend("All of Deep IQ's tokens get a free roll on the token chart (+0). These additional abilities are permanent.");
			for (Token t: Token.tokenList){
				t.processAbilities(t.consoleName, Engine.requestNumber());
			}
			
		} else if (roll == 11) {
			Engine.diq.life += 25;
			GUI.tabAppend("Deep IQ gains 25 life.");
		} else if (roll == 12) {
			GUI.tabAppend("Put a Progenitus token onto the battlefield.");
			t = new Token(10,10,16);
		} else if (roll == 13) {
			
			for (Token t : Token.tokenList){
				t.abilityList.add(Token.staticAbilities.Indestructable);
			}
			GUI.tabAppend("All of Deep IQ's permanents are indestructable.");
		} else if (roll >= 14) {
			GUI.tabAppend("Destroy all of your permananents");
		}

	}

	public interface Function {
		void Action();
	}

}
